package org.jwechat.api.proxy.service.mp.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.jwechat.common.enums.LangEnum;
import org.jwechat.api.proxy.service.mp.WxMpOAuthService;
import org.jwechat.common.bean.common.WxMpResult;
import org.jwechat.common.config.RedisUtil;
import org.jwechat.common.config.WxMpConfig;
import org.jwechat.common.util.WxHttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Title WxMpOAuthServiceImpl
 * @Description 微信公众号OAuth2.0授权服务
 * @Author ZhangKai
 * @Date 2020/4/7 0007
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@Service
@Slf4j
public class WxMpOAuthServiceImpl implements WxMpOAuthService {

    @Autowired
    private WxMpConfig wxMpConfig;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public WxMpResult getOAuthAccessToken(String code) {
        Map<String, Object> params = new HashMap<>();
        params.put("appid",wxMpConfig.getAppid());
        params.put("secret",wxMpConfig.getSecret());
        params.put("code",code);
        params.put("grant_type","authorization_code");
        String data = WxHttpUtil.httpGet(GET_ACCESS_TOKEN_URL, params);
        JSONObject accessTokenJSON = JSONUtil.parseObj(data);
        String accessToken = accessTokenJSON.getStr("access_token");

        if (StrUtil.isBlank(accessToken)) {
            log.error("获取OAuth的access_token失败-->{}",accessTokenJSON);
            return JSONUtil.toBean(accessTokenJSON, WxMpResult.class);
        }

        log.info("获取OAuth的access_token成功-->{}",accessTokenJSON);
        String openId = accessTokenJSON.getStr("openid");
        String refreshToken = accessTokenJSON.getStr("refresh_token");
        Integer expiresIn = accessTokenJSON.getInt("expires_in");
        redisUtil.setEx(openId.concat("_oauth_access_token"),accessToken,expiresIn,TimeUnit.SECONDS);
        log.info("获取OAuth用户[{}]的access_token,并刷入缓存[{}],缓存时间{}秒",openId,accessToken,expiresIn);
        redisUtil.setEx(openId.concat("_oauth_refresh_token"),refreshToken,30, TimeUnit.DAYS);
        log.info("获取OAuth用户[{}]的refresh_token,并刷入缓存[{}],缓存时间30天",openId,refreshToken);
        WxMpResult wxMpResult = new WxMpResult();
        wxMpResult.setErrcode(0);
        wxMpResult.setErrmsg("获取成功");
        JSONObject dataObj = new JSONObject();
        JSONObject openid = dataObj.put("openid", openId);
        wxMpResult.setData(openid);
        return wxMpResult;
    }

    @Override
    public WxMpResult getOAuthRefreshToken(String openId) {
        Object oauthRefreshToken = redisUtil.get(openId.concat("_oauth_refresh_token"));
        if (ObjectUtil.isNull(oauthRefreshToken)) {
            WxMpResult wxMpResult = new WxMpResult();
            wxMpResult.setErrcode(-100);
            wxMpResult.setErrmsg("该用户未授权或缓存已失效,请重新授权");
            return wxMpResult;
        }
        String refreshToken = (String) oauthRefreshToken;
        Map<String, Object> params = new HashMap<>();
        params.put("appid",wxMpConfig.getAppid());
        params.put("grant_type","refresh_token");
        params.put("refresh_token",refreshToken);
        String data = WxHttpUtil.httpGet(GET_REFRESH_TOKEN_URL, params);
        JSONObject accessTokenJSON = JSONUtil.parseObj(data);
        String accessToken = accessTokenJSON.getStr("access_token");
        if (StrUtil.isBlank(accessToken)) {
            log.error("获取OAuth的access_token失败-->{}",accessTokenJSON);
            return JSONUtil.toBean(accessTokenJSON, WxMpResult.class);
        }
        log.info("获取OAuth的access_token成功-->{}",accessTokenJSON);
        refreshToken = accessTokenJSON.getStr("refresh_token");
        Integer expiresIn = accessTokenJSON.getInt("expires_in");
        redisUtil.setEx(openId.concat("_oauth_access_token"),accessToken,expiresIn,TimeUnit.SECONDS);
        log.info("获取OAuth用户[{}]的access_token,并刷入缓存[{}],缓存时间{}秒",openId,accessToken,expiresIn);
        redisUtil.setEx(openId.concat("_oauth_refresh_token"),refreshToken,30, TimeUnit.DAYS);
        log.info("获取OAuth用户[{}]的refresh_token,并刷入缓存[{}],缓存时间30天",openId,refreshToken);
        WxMpResult wxMpResult = new WxMpResult();
        wxMpResult.setErrcode(0);
        wxMpResult.setErrmsg("获取成功");
        JSONObject dataObj = new JSONObject();
        JSONObject openid = dataObj.put("openid", openId);
        wxMpResult.setData(openid);
        return wxMpResult;
    }

    @Override
    public WxMpResult getOAuthUserInfo(String openId, LangEnum langEnum) {
        Object oauthAccessToken = redisUtil.get(openId.concat("_oauth_access_token"));
        if (ObjectUtil.isNull(oauthAccessToken)) {
            WxMpResult wxMpResult = new WxMpResult();
            wxMpResult.setErrcode(-100);
            wxMpResult.setErrmsg("该用户未授权或该用户token已过期,请刷新token");
            return wxMpResult;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("access_token",oauthAccessToken);
        params.put("openid",openId);
        params.put("lang",langEnum.getCode());
        String data = WxHttpUtil.httpGet(GET_USERINFO_URL, params);
        JSONObject userInfoJSON = JSONUtil.parseObj(data);
        String unionid = userInfoJSON.getStr("unionid");
        if (StrUtil.isBlank(unionid)) {
            log.error("获取OAuth的用户信息失败-->{}",userInfoJSON);
            return JSONUtil.toBean(userInfoJSON, WxMpResult.class);
        }
        WxMpResult wxMpResult = new WxMpResult();
        wxMpResult.setErrcode(0);
        wxMpResult.setErrmsg("获取成功");
        wxMpResult.setData(userInfoJSON);
        log.info("获取OAuth的用户信息成功-->{}",userInfoJSON);
        return wxMpResult;
    }

    @Override
    public WxMpResult checkOAuthAccessToken(String openId) {
        Object oauthAccessToken = redisUtil.get(openId.concat("_oauth_access_token"));
        if (ObjectUtil.isNull(oauthAccessToken)) {
            WxMpResult wxMpResult = new WxMpResult();
            wxMpResult.setErrcode(-100);
            wxMpResult.setErrmsg("该用户未授权或该用户token已过期,请刷新token");
            return wxMpResult;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("access_token",oauthAccessToken);
        params.put("openid",openId);
        String data = WxHttpUtil.httpGet(CHECK_ACCESS_TOKEN_URL, params);
        return JSONUtil.toBean(data, WxMpResult.class);
    }
}

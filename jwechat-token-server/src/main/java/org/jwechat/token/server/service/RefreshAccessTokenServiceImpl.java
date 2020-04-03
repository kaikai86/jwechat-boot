package org.jwechat.token.server.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.jwechat.common.bean.WxMpResult;
import org.jwechat.common.config.RedisUtil;
import org.jwechat.common.config.WxMpConfig;
import org.jwechat.common.util.WxHttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Title RefreshAccessTokenSchedule
 * @Description 被动刷新access_token定时器类
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@Service
@Slf4j
public class RefreshAccessTokenServiceImpl implements RefreshAccessTokenService{

    @Autowired
    private WxMpConfig wxConfig;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public WxMpResult refreshAccessTokenFromMP() {
        //1、通过HTTP接口刷新access_token
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("grant_type","client_credential");
        tokenMap.put("appid",wxConfig.getAppid());
        tokenMap.put("secret",wxConfig.getSecret());
        log.info("准备刷新access_token");
        String response = WxHttpUtil.httpGetJson(TOKEN_BASE_URL, tokenMap);
        JSONObject jsonObject = JSONUtil.parseObj(response);
        String accessToken = jsonObject.getStr("access_token");
        WxMpResult result;
        if (StrUtil.isNotBlank(accessToken)) {
            int expiresIn = jsonObject.getInt("expires_in");
            //2、缓存access_token
            redisUtil.setEx("access_token",accessToken,expiresIn, TimeUnit.SECONDS);
            result = new WxMpResult();
            result.setErrcode(0);
            result.setErrmsg("ok");
            log.info("刷新access_token成功");
        }else{
            result = JSONUtil.toBean(response, WxMpResult.class);
            log.info("刷新access_token失败");
            log.info("刷新access_token失败结果:--> {}",result);
        }
        return result;
    }
}

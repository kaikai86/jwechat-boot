package org.jwechat.token.server.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.jwechat.common.bean.WxMpResult;
import org.jwechat.common.config.RedisUtil;
import org.jwechat.common.config.WxCorpConfig;
import org.jwechat.common.util.WxHttpUtil;
import org.jwechat.token.server.service.CorpAccessTokenService;
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
public class CorpAccessTokenServiceImpl implements CorpAccessTokenService {

    @Autowired
    private WxCorpConfig wxConfig;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public WxMpResult getAccessTokenByAgentIdFromCorp(String agentid) {
        WxMpResult result = new WxMpResult();;
        if (!wxConfig.isEnabled()) {
            result.setErrcode(-2000);
            result.setErrmsg("企业微信功能未开启");
            return result;
        }
        //1、通过HTTP接口刷新access_token
        Map<String, String> secrets = wxConfig.getSecrets();
        if (MapUtil.isEmpty(secrets)) {
            result.setErrcode(0);
            result.setErrmsg("未找到agentid（应用id）为：".concat(agentid).concat("的secret"));
            log.info("获取access_token失败结果:--> {}",JSONUtil.toJsonStr(result));
            return result;
        }
        Map<String, Object> tokenMap = new HashMap<>();
        String secret = wxConfig.getSecrets().get(agentid);
        String corpid = wxConfig.getCorpid();
        tokenMap.put("corpid",wxConfig.getCorpid());
        tokenMap.put("corpsecret", secret);
        log.info("准备获取access_token");
        String response = WxHttpUtil.httpGetJson(TOKEN_BASE_URL, tokenMap);
        JSONObject jsonObject = JSONUtil.parseObj(response);
        String accessToken = jsonObject.getStr("access_token");
        if (StrUtil.isNotBlank(accessToken)) {
            log.info("获取的access_token为：{}",accessToken);
            int expiresIn = jsonObject.getInt("expires_in");
            //2、缓存access_token
            String accessTokenKey = corpid.concat(":").concat(agentid);
            redisUtil.setEx(accessTokenKey,accessToken,expiresIn, TimeUnit.SECONDS);
            log.info("缓存access_token成功");
            result.setErrcode(0);
            result.setErrmsg("ok");
            JSONObject jsonData = new JSONObject();
            jsonData.put("access_token", accessToken);
            result.setData(jsonData);

        }else{
            result = JSONUtil.toBean(response, WxMpResult.class);
            log.info("获取access_token失败结果:--> {}",result);
        }
        return result;
    }
}

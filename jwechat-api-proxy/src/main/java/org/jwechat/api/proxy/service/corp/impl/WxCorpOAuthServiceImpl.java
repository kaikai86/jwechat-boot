package org.jwechat.api.proxy.service.corp.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.jwechat.api.proxy.service.corp.WxCorpOAuthService;
import org.jwechat.common.bean.common.WxCorpMessageResult;
import org.jwechat.common.bean.common.WxCorpOAuthResult;
import org.jwechat.common.bean.common.WxCorpResult;
import org.jwechat.common.bean.common.WxMpResult;
import org.jwechat.common.util.WxHttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title WxCorpOAuthServiceImpl
 * @Description 企业微信网页登录授权服务接口实现类
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@Service
@Slf4j
public class WxCorpOAuthServiceImpl implements WxCorpOAuthService {
    @Autowired
    private RefreshCorpAccessTokenServiceImpl refreshCorpAccessTokenService;
    @Override
    public WxCorpOAuthResult getOAuthUserInfo(String agentId,String code) {
        String accessToken = refreshCorpAccessTokenService.getAccessToken(agentId);
        Map<String, Object> params = new HashMap<>();
        params.put("access_token",accessToken);
        params.put("code",code);
        String oauthResult = WxHttpUtil.httpGetJson(GET_USER_INFO_URL, params);
        log.info("获取用户信息成功-->应用id:{}，code码:{}，用户信息：{}",agentId,code,oauthResult);
        return JSONUtil.toBean(oauthResult, WxCorpOAuthResult.class);

    }
}

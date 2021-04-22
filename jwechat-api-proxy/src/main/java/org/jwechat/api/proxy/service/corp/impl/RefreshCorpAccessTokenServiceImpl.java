package org.jwechat.api.proxy.service.corp.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.jwechat.api.proxy.client.TokenServerApiClient;
import org.jwechat.api.proxy.service.corp.CorpAccessTokenService;
import org.jwechat.common.bean.common.WxMpResult;
import org.jwechat.common.config.RedisUtil;
import org.jwechat.common.config.WxCorpConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title RefreshAccessTokenServiceImpl
 * @Description 重新获取access_token服务类
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@Service
@Slf4j
public class RefreshCorpAccessTokenServiceImpl implements CorpAccessTokenService {

    @Autowired
    private TokenServerApiClient tokenServerApiClient;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private WxCorpConfig wxCorpConfig;

    @Override
    public boolean refreshAccessToken(String agentId) {
        boolean result = false;
        WxMpResult wxMpResult = tokenServerApiClient.refreshCORP(agentId);
        log.info("客户端被动刷新返回结果：{}", JSONUtil.toJsonStr(wxMpResult));
        Integer errCode = wxMpResult.getErrcode();
        if (0 == errCode) {
            result = true;
        }
        return result;
    }

    @Override
    public String getAccessToken(String agentId) {
        String accessTokenKey = wxCorpConfig.getCorpid().concat(":").concat(agentId);
        String accessToken = (String) redisUtil.get(accessTokenKey);
        if (StrUtil.isBlank(accessToken)) {
            log.info("缓存获取CORP|access_token失败~");
            if(this.refreshAccessToken(agentId)){
                accessToken = (String) redisUtil.get(accessTokenKey);
                log.info("CORP|access_token刷新成功~新token为{}",accessToken);
            }else{
                log.info("重新获取缓存CORP|access_token失败~");
                throw new RuntimeException("重新获取CORP|access_token失败");
            }
        }
        return accessToken;
    }

    @Override
    public String getAccessToken(String corpId, String agentId, String secret) {
        String accessTokenKey = corpId.concat(":").concat(agentId);
        String accessToken = (String) redisUtil.get(accessTokenKey);
        if (StrUtil.isBlank(accessToken)) {
            log.info("缓存获取CORP|access_token失败~");
            if(this.refreshAccessToken(corpId,agentId,secret)){
                accessToken = (String) redisUtil.get(accessTokenKey);
                log.info("CORP|access_token刷新成功~新token为{}",accessToken);
            }else{
                log.info("重新获取缓存CORP|access_token失败~");
                throw new RuntimeException("重新获取CORP|access_token失败");
            }
        }
        return accessToken;
    }

    @Override
    public boolean refreshAccessToken(String corpId, String agentId, String secret) {
        boolean result = false;
        WxMpResult wxMpResult = tokenServerApiClient.refreshCORP(corpId,agentId,secret);
        log.info("客户端被动刷新返回结果：{}", JSONUtil.toJsonStr(wxMpResult));
        Integer errCode = wxMpResult.getErrcode();
        if (0 == errCode) {
            result = true;
        }
        return result;
    }

}

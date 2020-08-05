package org.jwechat.api.proxy.service.mp.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.jwechat.api.proxy.client.TokenServerApiClient;
import org.jwechat.api.proxy.service.mp.RefreshAccessTokenService;
import org.jwechat.common.bean.common.WxMpResult;
import org.jwechat.common.config.RedisUtil;
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
public class RefreshAccessTokenServiceImpl implements RefreshAccessTokenService {

    @Autowired
    private TokenServerApiClient tokenServerApiClient;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean refreshAccessToken() {
        boolean result = false;
        WxMpResult wxMpResult = tokenServerApiClient.refreshMP();
        log.info("客户端被动刷新返回结果：{}", JSONUtil.toJsonStr(wxMpResult));
        Integer errCode = wxMpResult.getErrcode();
        if (0 == errCode) {
            result = true;
        }
        return result;
    }

    @Override
    public String getAccessToken() {
        String accessToken = (String) redisUtil.get("access_token");
        if (StrUtil.isBlank(accessToken)) {
            log.info("缓存获取access_token失败~");
            if(this.refreshAccessToken()){
                accessToken = (String) redisUtil.get("access_token");
                log.info("access_token刷新成功~新token为{}",accessToken);
            }else{
                log.info("重新获取缓存access_token失败~");
//                throw new RuntimeException("");
            }
        }
        return accessToken;
    }
}

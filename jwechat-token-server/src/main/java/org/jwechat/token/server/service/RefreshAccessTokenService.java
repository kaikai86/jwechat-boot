package org.jwechat.token.server.service;

import org.jwechat.common.bean.common.WxMpResult;

/**
 * @Title RefreshAccessTokenService
 * @Description 刷新access_token服务类
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
public interface RefreshAccessTokenService {

    String MP_TOKEN_BASE_URL="https://api.weixin.qq.com/cgi-bin/token";
    String CORP_TOKEN_BASE_URL="https://qyapi.weixin.qq.com/cgi-bin/gettoken";

    WxMpResult refreshAccessTokenFromMP();

    WxMpResult refreshAccessTokenFromCORP(String agentid);
    /**
     * 多租户刷新access_token
     * @param corpid
     * @param agentid
     * @param secret
     * @return
     */
    WxMpResult refreshAccessTokenFromCORP(String corpid,String agentid,String secret);
}

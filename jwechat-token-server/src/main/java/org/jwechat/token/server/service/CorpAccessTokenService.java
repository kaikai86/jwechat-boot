package org.jwechat.token.server.service;

import org.jwechat.common.bean.WxMpResult;

/**
 * @Title CorpAccessTokenService
 * @Description 企业微信获取access_token服务类
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
public interface CorpAccessTokenService {

    String TOKEN_BASE_URL="https://qyapi.weixin.qq.com/cgi-bin/gettoken";

    /**
     * 通过agetnid获取accessToken
     * @param agentid
     * @return
     */
     WxMpResult getAccessTokenByAgentIdFromCorp(String agentid);
}

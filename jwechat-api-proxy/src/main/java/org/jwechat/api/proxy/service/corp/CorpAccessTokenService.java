package org.jwechat.api.proxy.service.corp;

/**
 * @Title RefreshAccessTokenService
 * @Description 重新获取access_token服务类
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
public interface CorpAccessTokenService {

    String getAccessToken(String agentId);

    boolean refreshAccessToken(String agentId);

    String getAccessToken(String corpId,String agentId,String secret);

    boolean refreshAccessToken(String corpId,String agentId,String secret);

}

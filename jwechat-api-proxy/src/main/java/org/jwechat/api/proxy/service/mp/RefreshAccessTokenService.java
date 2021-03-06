package org.jwechat.api.proxy.service.mp;

/**
 * @Title RefreshAccessTokenService
 * @Description 重新获取access_token服务类
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
public interface RefreshAccessTokenService {

    boolean refreshAccessToken();

    String getAccessToken();

}

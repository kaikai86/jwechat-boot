package org.jwechat.token.server.service;

import org.jwechat.common.bean.WxMpResult;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title RefreshAccessTokenService
 * @Description 刷新access_token服务类
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
public interface RefreshAccessTokenService {

    String TOKEN_BASE_URL="https://api.weixin.qq.com/cgi-bin/token";

    public WxMpResult refreshAccessTokenFromMP();
}

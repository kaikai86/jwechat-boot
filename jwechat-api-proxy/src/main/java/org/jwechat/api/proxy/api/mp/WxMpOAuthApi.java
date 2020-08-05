package org.jwechat.api.proxy.api.mp;

import org.jwechat.common.enums.LangEnum;
import org.jwechat.api.proxy.service.mp.WxMpOAuthService;
import org.jwechat.common.bean.common.WxMpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Title WxMpOAuthApi
 * @Description 微信公众号登录授权服务API
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@RestController
@RequestMapping("/oauth")
public class WxMpOAuthApi {

    @Autowired
    private WxMpOAuthService wxMpOAuthService;

    @GetMapping("/access_token")
    public WxMpResult getAccessToken(String code) {
        return wxMpOAuthService.getOAuthAccessToken(code);
    }

    @GetMapping("/refresh_token")
    public WxMpResult getRefreshToken(String openId) {
        return wxMpOAuthService.getOAuthRefreshToken(openId);
    }

    @GetMapping("/userinfo")
    public WxMpResult getUserInfo(String openId) {
        return wxMpOAuthService.getOAuthUserInfo(openId, LangEnum.ZH_CN);
    }

    @GetMapping("/auth")
    public WxMpResult auth(String openId) {
        return wxMpOAuthService.checkOAuthAccessToken(openId);
    }

}

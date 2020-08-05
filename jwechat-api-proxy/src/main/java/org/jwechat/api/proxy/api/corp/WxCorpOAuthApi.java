package org.jwechat.api.proxy.api.corp;

import lombok.extern.slf4j.Slf4j;
import org.jwechat.api.proxy.service.corp.WxCorpOAuthService;
import org.jwechat.api.proxy.service.mp.WxMpOAuthService;
import org.jwechat.common.bean.common.WxCorpResult;
import org.jwechat.common.bean.common.WxMpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Title WxCorpOAuthApi
 * @Description 企业微信网页登录授权服务API
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@RestController
@Slf4j
@RequestMapping("/corp/oauth")
public class WxCorpOAuthApi {

    @Autowired
    private WxCorpOAuthService wxCorpOAuthService;

    @GetMapping("/userinfo")
    public WxCorpResult getUserInfo(String agentId, String code) {
        return wxCorpOAuthService.getOAuthUserInfo(agentId, code);
    }
}

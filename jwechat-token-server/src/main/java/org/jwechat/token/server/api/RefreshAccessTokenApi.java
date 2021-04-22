package org.jwechat.token.server.api;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.jwechat.common.bean.common.WxMpResult;
import org.jwechat.token.server.service.RefreshAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title RefreshAccessTokenApi
 * @Description 被动刷新access_tokenAPI类
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@RestController
@Slf4j
public class RefreshAccessTokenApi {

    @Autowired
    private RefreshAccessTokenService refreshAccessTokenService;

    @GetMapping("/mp/refresh")
    public WxMpResult refreshAccessToken() {
         return refreshAccessTokenService.refreshAccessTokenFromMP();
    }

    @GetMapping("/corp/refresh")
    public WxMpResult refreshCorpAccessToken(String agentId) {
        return refreshAccessTokenService.refreshAccessTokenFromCORP(agentId);
    }

    @GetMapping("/tenant_corp/refresh")
    public WxMpResult refreshCorpAccessToken(String corpId,String agentId,String secret) {
        return refreshAccessTokenService.refreshAccessTokenFromCORP(corpId,agentId,secret);
    }
}

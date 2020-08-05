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
        WxMpResult result = refreshAccessTokenService.refreshAccessTokenFromMP();
        log.info("[{}] 主动刷新access_token: --> {}", DateUtil.now(),result);
        return result;
    }

    @GetMapping("/corp/refresh")
    public WxMpResult refreshCorpAccessToken(@RequestParam(required = true) String agentId) {
        WxMpResult result = refreshAccessTokenService.refreshAccessTokenFromCORP(agentId);
        log.info("[{}] 主动刷新access_token: --> {}", DateUtil.now(),result);
        return result;
    }
}

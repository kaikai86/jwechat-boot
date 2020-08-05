package org.jwechat.token.server.api;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.jwechat.common.bean.WxMpResult;
import org.jwechat.token.server.service.CorpAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title CorpAccessTokenApi
 * @Description 获取企业微信access_tokenAPI类
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@RestController
@Slf4j
@RequestMapping("/corp")
public class CorpAccessTokenApi {

    @Autowired
    private CorpAccessTokenService corpAccessTokenService;


    @GetMapping("/token")
    public WxMpResult getCorpAccessToken(@RequestParam(required = true) String agentId) {
        WxMpResult result = corpAccessTokenService.getAccessTokenByAgentIdFromCorp(agentId);
        log.info("[{}] 获取access_token: --> {}", DateUtil.now(),result);
        return result;
    }
}

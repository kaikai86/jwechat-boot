package org.jwechat.token.server.schedule;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.jwechat.common.bean.WxMpResult;
import org.jwechat.common.config.WxCorpConfig;
import org.jwechat.token.server.service.CorpAccessTokenService;
import org.jwechat.token.server.service.RefreshAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @Title RefreshAccessTokenSchedule
 * @Description 被动刷新access_token定时器类
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@Component
@Slf4j
public class RefreshAccessTokenSchedule {

    @Autowired
    private RefreshAccessTokenService refreshAccessTokenService;
    @Autowired
    private CorpAccessTokenService corpAccessTokenService;
    @Autowired
    private WxCorpConfig wxCorpConfig;

    /**
     * 定时任务：每隔90秒获取并更新
     */
//    @Scheduled(cron = "0 0/3 * * * ?")
//    @Scheduled(cron = "30 1/3 * * * ?")
    /**
     * 定时任务：每隔90分钟获取并更新
     */
    @Scheduled(cron = "0 0 0/3 * * ?")
    @Scheduled(cron = "0 30 1/3 * * ?")
    public void refreshAccessToken() {
        WxMpResult result = refreshAccessTokenService.refreshAccessTokenFromMP();
    }

    /**
     * 定时任务：每隔90分钟获取并更新
     */
    @Scheduled(cron = "0 0 0/3 * * ?")
    @Scheduled(cron = "0 30 1/3 * * ?")
    public void refreshCorpAccessToken() {
        Map<String, String> secrets = wxCorpConfig.getSecrets();
        for (String agentId : secrets.keySet()) {
            corpAccessTokenService.getAccessTokenByAgentIdFromCorp(agentId);
        }
    }
}

package org.jwechat.common.config;


import cn.hutool.json.JSONUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * @Title WxCorpConfig
 * @Description 企业微信配置类
 * @Author ZhangKai
 * @Date 2020/4/1 0001
 * @Version 1.0
 * @Email 410618538@qq.com
 */

@Component
@ConfigurationProperties(prefix = "weixin.corp"/*,ignoreInvalidFields=true, ignoreUnknownFields=true*/)
@Getter
@Setter
@Slf4j
public class WxCorpConfig implements InitializingBean {

    private String corpid;
    private Map<String, String> secrets;
    private boolean enabled = Boolean.FALSE;


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("-----------------------> 企业微信启用状态:{} <-----------------------",this.enabled?"启用":"未启用");
        if (this.enabled) {
            log.info("-----------------------> 当前配置企业微信corpid:{} <-----------------------",this.corpid);
            log.info("-----------------------> 当前配置企业微信应用信息:{} <-----------------------", JSONUtil.toJsonStr(secrets));
        }
    }
}

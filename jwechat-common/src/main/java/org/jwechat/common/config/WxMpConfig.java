package org.jwechat.common.config;


import cn.hutool.json.JSONUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Title WxMpConfig
 * @Description 微信公众号配置类
 * @Author ZhangKai
 * @Date 2020/4/1 0001
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@Component
@ConfigurationProperties(prefix = "weixin.mp"/*,ignoreInvalidFields=true, ignoreUnknownFields=true*/)
@Getter
@Setter
@Slf4j
public class WxMpConfig  implements InitializingBean {

    private String appid;
    private String secret;
    private String encodingaeskey;
    private String token;
    private boolean enabled = Boolean.FALSE;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("-----------------------> 微信公众号启用状态:{} <-----------------------",this.enabled?"启用":"未启用");
        if (this.enabled) {
            log.info("-----------------------> 当前配置微信公众号appid:{} <-----------------------",this.appid);
            log.info("-----------------------> 当前配置微信公众号秘钥:{} <-----------------------", this.secret);
            log.info("-----------------------> 当前配置微信公众号随机数:{} <-----------------------", this.encodingaeskey);
            log.info("-----------------------> 当前配置微信公众号token:{} <-----------------------", this.token);
        }

    }
}

package org.jwechat.api.proxy.api.mp;

import lombok.extern.slf4j.Slf4j;
import org.jwechat.common.enums.LangEnum;
import org.jwechat.api.proxy.service.mp.WxMpUserService;
import org.jwechat.common.bean.common.WxMpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title WxMpMediaApi
 * @Description 微信公众号用户服务API
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@RestController
@Slf4j
public class WxMpUserApi {

    @Autowired
    private WxMpUserService wxMpUserService;

    @GetMapping("/users/{openid}")
    public WxMpResult get(@PathVariable String openid) {
        return wxMpUserService.getWxMpUserInfo(openid, LangEnum.ZH_CN);
    }
}

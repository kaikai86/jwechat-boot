package org.jwechat.api.proxy.api.corp;

import lombok.extern.slf4j.Slf4j;
import org.jwechat.api.proxy.service.corp.WxCorpMessageService;
import org.jwechat.common.bean.common.WxCorpResult;
import org.jwechat.common.bean.corp.message.WxCorpTaskCardMessage;
import org.jwechat.common.bean.corp.message.WxCorpTextCardMessage;
import org.jwechat.common.bean.corp.message.WxCorpTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title WxCorpMessageApi
 * @Description 企业微信消息推送服务API
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@RestController
@Slf4j
@RequestMapping("/corp/messages")
public class WxCorpMessageApi {

    @Autowired
    private WxCorpMessageService wxCorpMessageService;

    @PostMapping("/text")
    public WxCorpResult text(@RequestBody WxCorpTextMessage wxCorpTextMessage) {
        return wxCorpMessageService.sendMessage(wxCorpTextMessage);
    }

    @PostMapping("/taskcard")
    public WxCorpResult taskcard(@RequestBody WxCorpTaskCardMessage wxCorpTaskCardMessage) {
        return wxCorpMessageService.sendMessage(wxCorpTaskCardMessage);
    }

    @PostMapping("/textcard")
    public WxCorpResult textcard(@RequestBody WxCorpTextCardMessage wxCorpTextCardMessage) {
        return wxCorpMessageService.sendMessage(wxCorpTextCardMessage);
    }
}

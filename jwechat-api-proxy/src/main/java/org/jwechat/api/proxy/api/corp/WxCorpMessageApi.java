package org.jwechat.api.proxy.api.corp;

import lombok.extern.slf4j.Slf4j;
import org.jwechat.api.proxy.service.corp.message.WxCorpMessageService;
import org.jwechat.common.bean.common.WxCorpResult;
import org.jwechat.common.bean.corp.message.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/tenant_text")
    public WxCorpResult tenant_text(@RequestParam String corpId, @RequestParam String secret,@RequestBody WxCorpTextMessage wxCorpTextMessage) {
        return wxCorpMessageService.sendMessage(corpId,secret,wxCorpTextMessage);
    }

    @PostMapping("/tenant_textcard")
    public WxCorpResult tenant_textcard(@RequestParam String corpId, @RequestParam String secret,@RequestBody WxCorpTextCardMessage wxCorpTextCardMessage) {
        return wxCorpMessageService.sendMessage(corpId,secret,wxCorpTextCardMessage);
    }

    @PostMapping("/tenant_file")
    public WxCorpResult tenant_file(@RequestParam String corpId, @RequestParam String secret,@RequestBody WxCorpFileMessage wxCorpFileMessage) {
        return wxCorpMessageService.sendMessage(corpId,secret,wxCorpFileMessage);
    }

}

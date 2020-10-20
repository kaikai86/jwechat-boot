package org.jwechat.api.proxy.api.corp;

import lombok.extern.slf4j.Slf4j;
import org.jwechat.api.proxy.service.corp.message.WxCorpMessageService;
import org.jwechat.api.proxy.service.corp.oa.WxCorpOAClockInService;
import org.jwechat.common.bean.common.WxCorpResult;
import org.jwechat.common.bean.corp.message.WxCorpTaskCardMessage;
import org.jwechat.common.bean.corp.message.WxCorpTextCardMessage;
import org.jwechat.common.bean.corp.message.WxCorpTextMessage;
import org.jwechat.common.bean.corp.oa.WxCorpOAClockIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title WxCorpOAClockInApi
 * @Description 企业微信OA打卡数据API
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@RestController
@Slf4j
@RequestMapping("/corp/clockin")
public class WxCorpOAClockInApi {

    @Autowired
    private WxCorpOAClockInService wxCorpOAClockInService;

    @PostMapping("/data")
    public WxCorpResult data(@RequestBody WxCorpOAClockIn wxCorpOAClockIn) {
        return wxCorpOAClockInService.getCheckInData(wxCorpOAClockIn);
    }

}

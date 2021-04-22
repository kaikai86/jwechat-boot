package org.jwechat.api.proxy.service.corp.message;

import org.jwechat.common.bean.common.WxCorpResult;
import org.jwechat.common.bean.corp.message.WxCorpMessage;

/**
 * @Title WxCorpMessageService
 * @Description 企业微信消息推送服务接口
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
public interface WxCorpMessageService {

    /**
     * 主动发送应用消息：企业后台调用接口通过应用向指定成员发送单聊消息
     */
    String SEND_MESSAGE_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send";

    WxCorpResult sendMessage(WxCorpMessage wxCorpMessage);

    WxCorpResult sendMessage(String corpId,String secret,WxCorpMessage wxCorpMessage);
}

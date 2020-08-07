package org.jwechat.api.proxy.service.corp.impl;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jwechat.api.proxy.service.corp.WxCorpMessageService;
import org.jwechat.common.bean.common.WxCorpResult;
import org.jwechat.common.bean.common.WxCorpMessageResult;
import org.jwechat.common.bean.corp.message.WxCorpMessage;
import org.jwechat.common.util.WxHttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title WxCorpMessageServiceImpl
 * @Description 企业微信消息推送服务接口实现类
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@Service
@Slf4j
public class WxCorpMessageServiceImpl implements WxCorpMessageService {
    @Autowired
    private RefreshCorpAccessTokenServiceImpl refreshCorpAccessTokenService;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public WxCorpResult sendMessage(WxCorpMessage wxCorpMessage) {
        String accessToken = refreshCorpAccessTokenService.getAccessToken(wxCorpMessage.getAgentid());
        Map<String, Object> params = new HashMap<>();
        params.put("access_token",accessToken);
        WxCorpResult wxCorpResult = null;
        try {
            String messageResult = WxHttpUtil.httpPostJson(SEND_MESSAGE_URL, objectMapper.writeValueAsString(wxCorpMessage), params);
            log.info("待推送消息-->应用id:{}，推送人员:{},推送部门:{}，推送标签:{}，消息类型:{}，推送数据：{}",wxCorpMessage.getAgentid(),wxCorpMessage.getTouser(),wxCorpMessage.getToparty(),wxCorpMessage.getTotag(),wxCorpMessage.getMsgType(),JSONUtil.toJsonStr(wxCorpMessage));
            WxCorpMessageResult wxCorpMessageResult = JSONUtil.toBean(messageResult, WxCorpMessageResult.class);
            if (wxCorpMessageResult.getErrcode() != 0) {
                log.warn("消息推送失败-->错误码：{},错误信息：{}", wxCorpMessageResult.getErrcode(), wxCorpMessageResult.getErrmsg());
                return wxCorpMessageResult;
            }
            log.info("消息推送成功");
            return wxCorpMessageResult;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return wxCorpResult;
    }
}

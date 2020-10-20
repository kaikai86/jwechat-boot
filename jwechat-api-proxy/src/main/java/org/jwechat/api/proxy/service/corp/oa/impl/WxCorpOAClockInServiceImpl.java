package org.jwechat.api.proxy.service.corp.oa.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jwechat.api.proxy.service.corp.impl.RefreshCorpAccessTokenServiceImpl;
import org.jwechat.api.proxy.service.corp.oa.WxCorpOAClockInService;
import org.jwechat.common.bean.common.WxCorpMessageResult;
import org.jwechat.common.bean.common.WxCorpOAClockInResult;
import org.jwechat.common.bean.common.WxCorpResult;
import org.jwechat.common.bean.corp.oa.WxCorpOAClockIn;
import org.jwechat.common.config.WxCorpConfig;
import org.jwechat.common.util.WxDateUtil;
import org.jwechat.common.util.WxHttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title WxCorpOAClockInServiceImpl
 * @Description 企业微信获取OA打开数据服务接口实现类
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */

@Slf4j
@Service
public class WxCorpOAClockInServiceImpl implements WxCorpOAClockInService {

    @Autowired
    private RefreshCorpAccessTokenServiceImpl refreshCorpAccessTokenService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WxCorpConfig wxCorpConfig;

    @Override
    public WxCorpResult getCheckInData(WxCorpOAClockIn wxCorpOAClockIn) {
        String accessToken = refreshCorpAccessTokenService.getAccessToken(wxCorpConfig.getOaClockInAgentId());
        Map<String, Object> params = new HashMap<>();
        params.put("access_token",accessToken);
        WxCorpResult wxCorpResult = null;
        try {
            String messageResult = WxHttpUtil.httpPostJson(GET_CHECKIN_DATA_URL, objectMapper.writeValueAsString(wxCorpOAClockIn), params);
            log.info("获取打卡数据-->获取打卡数据类型:{},开始时间:{}，结束时间:{}，用户列表:{}",wxCorpOAClockIn.getOpencheckindatatype(), WxDateUtil.formatUnixTime(wxCorpOAClockIn.getStarttime()),WxDateUtil.formatUnixTime(wxCorpOAClockIn.getEndtime()),wxCorpOAClockIn.getUseridlist());
            WxCorpOAClockInResult wxCorpOAClockInResult = JSONUtil.toBean(messageResult, WxCorpOAClockInResult.class);
            if (wxCorpOAClockInResult.getErrcode() != 0) {
                log.warn("消息推送失败-->错误码：{},错误信息：{}", wxCorpOAClockInResult.getErrcode(), wxCorpOAClockInResult.getErrmsg());
                return wxCorpOAClockInResult;
            }
            log.info("获取打卡数剧成功-->:{}",JSONUtil.toJsonStr(wxCorpOAClockInResult.getCheckindata()));
            return wxCorpOAClockInResult;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return wxCorpResult;
    }

    public static void main(String[] args) {
        DateTime date = DateUtil.date(1492617600*1000L);
        String s = date.toDateStr();
        System.err.println(s);
    }
}

package org.jwechat.api.proxy.service.corp.oa.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jwechat.api.proxy.service.corp.impl.RefreshCorpAccessTokenServiceImpl;
import org.jwechat.api.proxy.service.corp.oa.WxCorpOAApproveService;
import org.jwechat.api.proxy.service.corp.oa.WxCorpOAClockInService;
import org.jwechat.common.bean.common.WxCorpOAApproveDetailResult;
import org.jwechat.common.bean.common.WxCorpOAApproveResult;
import org.jwechat.common.bean.common.WxCorpOAClockInResult;
import org.jwechat.common.bean.common.WxCorpResult;
import org.jwechat.common.bean.corp.oa.WxCorpOAApprove;
import org.jwechat.common.bean.corp.oa.WxCorpOAClockIn;
import org.jwechat.common.config.WxCorpConfig;
import org.jwechat.common.util.WxDateUtil;
import org.jwechat.common.util.WxHttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title WxCorpOAApproveServiceImpl
 * @Description 企业微信获取OA审批数据服务接口实现类
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */

@Slf4j
@Service
public class WxCorpOAApproveServiceImpl implements WxCorpOAApproveService {

    @Autowired
    private RefreshCorpAccessTokenServiceImpl refreshCorpAccessTokenService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WxCorpConfig wxCorpConfig;

    @Override
    public WxCorpResult getSpNoList(WxCorpOAApprove wxCorpOAApprove) {
        String accessToken = refreshCorpAccessTokenService.getAccessToken(wxCorpConfig.getOaApproveAgentId());
        Map<String, Object> params = new HashMap<>();
        params.put("access_token",accessToken);
        try {
            String messageResult = WxHttpUtil.httpPostJson(GET_SP_NO_LIST_URL, objectMapper.writeValueAsString(wxCorpOAApprove), params);
            log.info("获取审批编号数据-->开始时间:{}，结束时间:{}，筛选条件:{}", WxDateUtil.formatUnixTime(wxCorpOAApprove.getStarttime()),WxDateUtil.formatUnixTime(wxCorpOAApprove.getEndtime()),wxCorpOAApprove.getFilters().toString());
            WxCorpOAApproveResult wxCorpOAApproveResult = JSONUtil.toBean(messageResult, WxCorpOAApproveResult.class);
            if (wxCorpOAApproveResult.getErrcode() != 0) {
                log.warn("获取审批编号失败-->错误码：{},错误信息：{}", wxCorpOAApproveResult.getErrcode(), wxCorpOAApproveResult.getErrmsg());
                return wxCorpOAApproveResult;
            }
            log.info("获取审批编号成功-->{}",JSONUtil.toJsonStr(wxCorpOAApproveResult.getSp_no_list()));
            return wxCorpOAApproveResult;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public WxCorpResult getApproveDetailBySpNo(Long spNo) {
        String accessToken = refreshCorpAccessTokenService.getAccessToken(wxCorpConfig.getOaApproveAgentId());
        Map<String, Object> params = new HashMap<>();
        params.put("access_token",accessToken);
        String messageResult = WxHttpUtil.httpPostJson(GET_APPROVE_DETAIL_URL,JSONUtil.createObj().put("sp_no", spNo).toString(), params);
        log.info("获取审批详情数据-->审批编号:{}", spNo);
        WxCorpOAApproveDetailResult wxCorpOAApproveDetailResult = JSONUtil.toBean(messageResult, WxCorpOAApproveDetailResult.class);
        if (wxCorpOAApproveDetailResult.getErrcode() != 0) {
            log.warn("获取审批详情失败-->错误码：{},错误信息：{}", wxCorpOAApproveDetailResult.getErrcode(), wxCorpOAApproveDetailResult.getErrmsg());
            return wxCorpOAApproveDetailResult;
        }
        log.info("获取审批详情成功-->{}",JSONUtil.toJsonStr(wxCorpOAApproveDetailResult.getInfo()));
        return wxCorpOAApproveDetailResult;

    }


}

package org.jwechat.api.proxy.service.corp.oa;

import org.jwechat.common.bean.common.WxCorpResult;
import org.jwechat.common.bean.corp.message.WxCorpMessage;
import org.jwechat.common.bean.corp.oa.WxCorpOAClockIn;

/**
 * @Title WxCorpOAClockInService
 * @Description 企业微信获取OA打开数据服务接口
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
public interface WxCorpOAClockInService {

    /**
     * 获取企业微信打开应用数据
     */
    String GET_CHECKIN_DATA_URL = "https://qyapi.weixin.qq.com/cgi-bin/checkin/getcheckindata";

    WxCorpResult getCheckInData(WxCorpOAClockIn wxCorpOAClockIn);

}

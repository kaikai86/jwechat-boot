package org.jwechat.api.proxy.service.corp.oa;

import org.jwechat.common.bean.common.WxCorpResult;
import org.jwechat.common.bean.corp.oa.WxCorpOAApprove;
import org.jwechat.common.bean.corp.oa.WxCorpOAClockIn;

/**
 * @Title WxCorpOAApproveService
 * @Description 企业微信获取OA审批数据服务接口
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
public interface WxCorpOAApproveService {

    /**
     * 批量获取审批单号
     */
    String GET_SP_NO_LIST_URL = "https://qyapi.weixin.qq.com/cgi-bin/oa/getapprovalinfo";
    /**
     * 获取审批详情
     */
    String GET_APPROVE_DETAIL_URL = "https://qyapi.weixin.qq.com/cgi-bin/oa/getapprovaldetail";


    WxCorpResult getSpNoList(WxCorpOAApprove wxCorpOAApprove);

    WxCorpResult getApproveDetailBySpNo(Long spNo);

}

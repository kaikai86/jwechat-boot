package org.jwechat.api.proxy.api.corp;

import lombok.extern.slf4j.Slf4j;
import org.jwechat.api.proxy.service.corp.oa.WxCorpOAApproveService;
import org.jwechat.api.proxy.service.corp.oa.WxCorpOAClockInService;
import org.jwechat.common.bean.common.WxCorpResult;
import org.jwechat.common.bean.corp.oa.WxCorpOAApprove;
import org.jwechat.common.bean.corp.oa.WxCorpOAClockIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Title WxCorpOAApproveApi
 * @Description 企业微信OA审批数据API
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@RestController
@Slf4j
@RequestMapping("/corp/approve")
public class WxCorpOAApproveApi {

    @Autowired
    private WxCorpOAApproveService wxCorpOAApproveService;


    @PostMapping("/sp_no_list")
    public WxCorpResult spNoList(@RequestBody WxCorpOAApprove wxCorpOAApprove) {
        return wxCorpOAApproveService.getSpNoList(wxCorpOAApprove);
    }

    @GetMapping("/detail")
    public WxCorpResult detail(@RequestParam(name="spNo") Long spNo) {
        return wxCorpOAApproveService.getApproveDetailBySpNo(spNo);
    }

}

package org.jwechat.api.proxy.api.corp;

import lombok.extern.slf4j.Slf4j;
import org.jwechat.api.proxy.service.corp.media.WxCorpMediaService;
import org.jwechat.api.proxy.service.corp.message.WxCorpMessageService;
import org.jwechat.common.bean.common.WxCorpResult;
import org.jwechat.common.bean.corp.message.WxCorpFileMessage;
import org.jwechat.common.bean.corp.message.WxCorpTaskCardMessage;
import org.jwechat.common.bean.corp.message.WxCorpTextCardMessage;
import org.jwechat.common.bean.corp.message.WxCorpTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Title WxCorpMediaApi
 * @Description 企业微信上传临时文件服务API
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@RestController
@Slf4j
@RequestMapping("/corp/medias")
public class WxCorpMediaApi {

    @Autowired
    private WxCorpMediaService wxCorpMediaService;

    @GetMapping("/tenant_upload")
    public WxCorpResult upload(String corpId,String agentId,String secret,String type,String filePath) {
        return wxCorpMediaService.uploadMedia(corpId, agentId, secret, type, filePath);
    }


}

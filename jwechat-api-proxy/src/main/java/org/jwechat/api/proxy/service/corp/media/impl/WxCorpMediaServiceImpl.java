package org.jwechat.api.proxy.service.corp.media.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jwechat.api.proxy.service.corp.impl.RefreshCorpAccessTokenServiceImpl;
import org.jwechat.api.proxy.service.corp.media.WxCorpMediaService;
import org.jwechat.api.proxy.service.corp.message.WxCorpMessageService;
import org.jwechat.common.bean.common.WxCorpMediaResult;
import org.jwechat.common.bean.common.WxCorpMessageResult;
import org.jwechat.common.bean.common.WxCorpResult;
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
public class WxCorpMediaServiceImpl implements WxCorpMediaService {
    @Autowired
    private RefreshCorpAccessTokenServiceImpl refreshCorpAccessTokenService;

    @Override
    public WxCorpResult uploadMedia(String agentId,String type,String filePath,String fileName) {
        String accessToken = refreshCorpAccessTokenService.getAccessToken(agentId);
        return uploadFile(agentId, accessToken, type, filePath,fileName);
    }

    @Override
    public WxCorpResult uploadMedia(String corpId, String agentId, String secret,String type,String filePath,String fileName) {
        String accessToken = refreshCorpAccessTokenService.getAccessToken(corpId,agentId,secret);
        return uploadFile(agentId, accessToken, type, filePath,fileName);
    }

    private WxCorpResult uploadFile(String agentId,String accessToken,String type,String filePath,String fileName) {
        Map<String, Object> params = new HashMap<>();
        params.put("access_token",accessToken);
        params.put("type", type);
        String messageResult = WxHttpUtil.httpPostFile(UPLOAD_MEDIA_URL, filePath, fileName,params);
        log.info("待上传临时文件-->应用id:{}，文件类型:{},文件路径:{}，文件名称:{}",agentId,type,filePath,fileName);
        WxCorpMediaResult wxCorpMediaResult = JSONUtil.toBean(messageResult, WxCorpMediaResult.class);
        if (wxCorpMediaResult.getErrcode() != 0) {
            log.warn("临时文件上传失败-->错误码：{},错误信息：{}", wxCorpMediaResult.getErrcode(), wxCorpMediaResult.getErrmsg());
            return wxCorpMediaResult;
        }
        log.info("临时文件上传成功");
        return wxCorpMediaResult;
    }
}

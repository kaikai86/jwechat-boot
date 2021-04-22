package org.jwechat.api.proxy.service.corp.media;

import org.jwechat.common.bean.common.WxCorpResult;

/**
 * @Title WxCorpMediaService
 * @Description 企业微信上传临时素材接口
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
public interface WxCorpMediaService {

    /**
     * 上传临时素材
     */
//    https://qyapi.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE
    String UPLOAD_MEDIA_URL = "https://qyapi.weixin.qq.com/cgi-bin/media/upload";

    WxCorpResult uploadMedia(String agentId,String type,String filePath);

    WxCorpResult uploadMedia(String corpId,String agentId,String secret,String type,String filePath);
}

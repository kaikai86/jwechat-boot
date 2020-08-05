package org.jwechat.api.proxy.service.corp;

import org.jwechat.common.bean.common.WxCorpResult;

/**
 * @Title WxCorpOAuthService
 * @Description 企业微信网页登录授权服务接口实现类
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
public interface WxCorpOAuthService {

    /**
     * 通过code换取网页授权access_token,http请求方式：GET（请使用https协议）
     * 权限说明：
     * 跳转的域名须完全匹配access_token对应应用的可信域名，否则会返回50001错误。
     */
    String GET_USER_INFO_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo";

    /**
     * 根据code获取成员信息
     * @param agentId 应用id，用于获取
     * @param code  通过成员授权获取到的code，最大为512字节。每次成员授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期
     * @return
     */
    WxCorpResult getOAuthUserInfo(String agentId,String code);



}

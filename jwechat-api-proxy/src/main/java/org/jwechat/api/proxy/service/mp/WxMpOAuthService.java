package org.jwechat.api.proxy.service.mp;

import org.jwechat.common.bean.common.WxMpResult;
import org.jwechat.common.enums.LangEnum;

/**
 * @Title WxMpOAuthService
 * @Description 微信公众号OAuth2.0授权服务接口
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
public interface WxMpOAuthService {

    /**
     * 通过code换取网页授权access_token,http请求方式：GET（请使用https协议）
     */
    String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    /**
     * 刷新access_token（如果需要）,http请求方式：GET（请使用https协议）
     */
    String GET_REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
    /**
     * 拉取用户信息(需scope为 snsapi_userinfo),http请求方式：GET（请使用https协议）
     */
    String GET_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo";
    /**
     * 检验授权凭证（access_token）是否有效,http请求方式：GET（请使用https协议）
     */
    String CHECK_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/auth";

    /**
     * 通过code换取网页授权access_token
     * @param code code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期
     * @return
     */
    public WxMpResult getOAuthAccessToken(String code);

    /**
     * 刷新access_token（如果需要）
     * @param openId    用户的唯一标识
     * @return
     */
    public WxMpResult getOAuthRefreshToken(String openId);

    /**
     * 拉取用户信息(需scope为 snsapi_userinfo)
     * @param openId    用户的唯一标识
     * @param langEnum  返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
     * @return
     */
    public WxMpResult getOAuthUserInfo(String openId, LangEnum langEnum);

    /**
     * 检验授权凭证（access_token）是否有效
     * @param openId    用户的唯一标识
     * @return
     */
    public WxMpResult checkOAuthAccessToken(String openId);



}

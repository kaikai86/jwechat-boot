package org.jwechat.api.proxy.service;

import org.jwechat.api.proxy.bean.menu.WxMpMenu;
import org.jwechat.api.proxy.enums.LangEnum;
import org.jwechat.common.bean.WxMpResult;

/**
 * @Title WxMpUserService
 * @Description 微信公众号用户管理服务接口
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
public interface WxMpUserService {

    /**
     * 获取用户基本信息（包括UnionID机制）。http请求方式：GET（请使用https协议）
     */
    String GET_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info";
    /**
     * 开发者可通过该接口来批量获取用户基本信息。最多支持一次拉取100条。http请求方式: POST（请使用https协议）
     */
    String GET_MORE_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info/batchget";
    /**
     * 获取用户列表,http请求方式：GET
     */
    String GET_USER_LIST_URL = "https://api.weixin.qq.com/cgi-bin/user/get";

    /**
     * 设置用户备注名,http请求方式：POST
     */
    String UPDATE_USER_REMARK = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark";

    /**
     * 创建标签,http请求方式：POST
     */
    String CREATE_TAGS_URL = "https://api.weixin.qq.com/cgi-bin/tags/create";
    /**
     * 获取公众号已创建的标签,http请求方式：GET
     */
    String CET_TAGS_URL = "https://api.weixin.qq.com/cgi-bin/tags/get";
    /**
     * 编辑标签,http请求方式：POST
     */
    String UPDATE_TAGS_URL = "https://api.weixin.qq.com/cgi-bin/tags/update";
    /**
     * 删除标签,http请求方式：POST
     */
    String DELETE_TAGS_URL = "https://api.weixin.qq.com/cgi-bin/tags/delete";
    /**
     * 获取标签下粉丝列表,http请求方式：GET
     */
    String CET_USER_TAGS_URL = "https://api.weixin.qq.com/cgi-bin/user/tag/get";

    /**
     * 获取用户基本信息（包括UnionID机制）
     */
    WxMpResult getWxMpUserInfo(String openId, LangEnum langEnum);
}

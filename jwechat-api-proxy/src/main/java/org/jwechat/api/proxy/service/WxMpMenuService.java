package org.jwechat.api.proxy.service;

import org.jwechat.api.proxy.bean.menu.WxMpMenu;
import org.jwechat.common.bean.WxMpResult;

/**
 * @Title WxMpMenuService
 * @Description 微信公众号自定义菜单服务接口
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
public interface WxMpMenuService {

    /**
     * 创建自定义菜单,http请求方式：POST（请使用https协议）
     */
    String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create";
    /**
     * 获取自定义菜单配置,http请求方式: GET（请使用https协议）
     */
    String GET_MENU_INFO_URL = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info";
    /**
     * 删除当前使用的自定义菜单,http请求方式：GET
     * 注意，在个性化菜单时，调用此接口会删除默认菜单及全部个性化菜单。
     */
    String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete";
    /**
     * 查询自定义菜单的结构。http请求方式：GET
     * 注意，在设置了个性化菜单后，使用本自定义菜单查询接口可以获取默认菜单和全部个性化菜单信息。
     */
    String GET_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get";

    /**
     * 创建个性化菜单,http请求方式：POST（请使用https协议）
     */
    String ADD_CONDITIONAL_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/addconditional";
    /**
     * 删除个性化菜单,http请求方式：POST（请使用https协议）
     */
    String DEL_CONDITIONAL_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delconditional";
    /**
     * 测试个性化菜单匹配结果,http请求方式：POST（请使用https协议）
     */
    String TRY_MATCH_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/trymatch";

    /**
     * 创建微信公众号自定义菜单
     */
    WxMpResult createWxMpMenus(WxMpMenu wxMpMenu);

    /**
     * 查询微信公众号自定义菜单
     * 注：本接口将会提供公众号当前使用的自定义菜单的配置，如果公众号是通过API调用设置的菜单，则返回菜单的开发配置，而如果公众号是在公众平台官网通过网站功能发布菜单，则本接口返回运营者设置的菜单配置。
     */
    String getWxMpMenus();

    /**
     * 删除微信公众号自定义菜单
     * 注：使用接口创建自定义菜单后，开发者还可使用接口删除当前使用的自定义菜单。另请注意，在个性化菜单时，调用此接口会删除默认菜单及全部个性化菜单。
     */
    WxMpResult deleteWxMpMenus();
}

package org.jwechat.api.proxy.api.mp;

import lombok.extern.slf4j.Slf4j;
import org.jwechat.common.bean.mp.menu.WxMpMenu;
import org.jwechat.api.proxy.service.mp.WxMpMenuService;
import org.jwechat.common.bean.common.WxMpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Title WxMpMenuApi
 * @Description 微信公众号菜单服务API
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@RestController
@Slf4j
public class WxMpMenuApi {

    @Autowired
    private WxMpMenuService wxMpMenuService;

    @PostMapping("/menus")
    public WxMpResult create(@RequestBody WxMpMenu wxMpMenu) {
        return wxMpMenuService.createWxMpMenus(wxMpMenu);
    }

    @GetMapping("/menus")
    public String get() {
        return wxMpMenuService.getWxMpMenus();
    }

    @DeleteMapping("/menus")
    public WxMpResult delete() {
        return wxMpMenuService.deleteWxMpMenus();
    }

}

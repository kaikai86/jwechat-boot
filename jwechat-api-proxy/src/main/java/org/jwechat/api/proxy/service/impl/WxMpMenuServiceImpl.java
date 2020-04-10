package org.jwechat.api.proxy.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jwechat.api.proxy.bean.menu.WxMpMenu;
import org.jwechat.api.proxy.service.WxMpMenuService;
import org.jwechat.common.bean.WxMpResult;
import org.jwechat.common.config.RedisUtil;
import org.jwechat.common.util.WxHttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title WxMpMenuServiceImpl
 * @Description 微信公众号自定义菜单服务类
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@Service
@Slf4j
public class WxMpMenuServiceImpl implements WxMpMenuService {

    @Autowired
    private RefreshAccessTokenServiceImpl refreshAccessTokenService;
    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public WxMpResult createWxMpMenus(WxMpMenu wxMpMenu) {
        String accessToken = refreshAccessTokenService.getAccessToken();
        if (StrUtil.isBlank(accessToken)) {
            WxMpResult wxMpResult = new WxMpResult();
            wxMpResult.setErrcode(-100);
            wxMpResult.setErrmsg("未获取到accessToken,请联系系统管理员");
            return wxMpResult;
        }
        WxMpResult wxMpResult = this.requestCreatMenu(accessToken, wxMpMenu);
        if (0 == wxMpResult.getErrcode()) {
            log.info("创建菜单成功~");
        } else if (40014 == wxMpResult.getErrcode()) {
            log.info("access_token过期,尝试重新获取~");
            if(refreshAccessTokenService.refreshAccessToken()){
                accessToken = refreshAccessTokenService.getAccessToken();
                log.info("access_token刷新成功,尝试重新创建菜单~新token为{}",accessToken);
                wxMpResult = requestCreatMenu(accessToken,wxMpMenu);
                if (0 == wxMpResult.getErrcode()) {
                    log.info("重新创建菜单成功~");
                }else{
                    log.info("重新创建菜单失败，请联系系统管理人员");
                }
            }
        } else {
            log.info("创建菜单失败，请联系系统管理人员");
        }
        return wxMpResult;

    }

    private WxMpResult requestCreatMenu(String accessToken, WxMpMenu wxMpMenu) {
        Map<String, Object> params = new HashMap<>();
        params.put("access_token",accessToken);
        WxMpResult wxMpResult = null;
        try {
            String result = WxHttpUtil.httpPostJson(CREATE_MENU_URL, objectMapper.writeValueAsString(wxMpMenu), params);
            wxMpResult = JSONUtil.toBean(result, WxMpResult.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return wxMpResult;
    }

    private WxMpResult requestDeleteMenu(String accessToken) {
        Map<String, Object> params = new HashMap<>();
        params.put("access_token",accessToken);
        String result = WxHttpUtil.httpGetJson(DELETE_MENU_URL, params);
        WxMpResult wxMpResult = JSONUtil.toBean(result, WxMpResult.class);
        return wxMpResult;
    }


    @Override
    public String getWxMpMenus() {
        String accessToken = refreshAccessTokenService.getAccessToken();
        if (StrUtil.isBlank(accessToken)) {
            WxMpResult wxMpResult = new WxMpResult();
            wxMpResult.setErrcode(-100);
            wxMpResult.setErrmsg("未获取到accessToken,请联系系统管理员");
            return JSONUtil.toJsonStr(wxMpResult);
        }
        Map<String, Object> params = new HashMap<>();
        params.put("access_token",accessToken);
        return WxHttpUtil.httpGetJson(GET_MENU_INFO_URL, params);
    }

    @Override
    public WxMpResult deleteWxMpMenus() {
        String accessToken = refreshAccessTokenService.getAccessToken();
        if (StrUtil.isBlank(accessToken)) {
            WxMpResult wxMpResult = new WxMpResult();
            wxMpResult.setErrcode(-100);
            wxMpResult.setErrmsg("未获取到accessToken,请联系系统管理员");
            return wxMpResult;
        }
        WxMpResult wxMpResult = this.requestDeleteMenu(accessToken);
        if (0 == wxMpResult.getErrcode()) {
            log.info("删除菜单成功~");
        } else if (40014 == wxMpResult.getErrcode()) {
            log.info("access_token过期,尝试重新获取~");
            if(refreshAccessTokenService.refreshAccessToken()){
                accessToken = refreshAccessTokenService.getAccessToken();
                log.info("access_token刷新成功,尝试重新删除菜单~新token为{}",accessToken);
                wxMpResult = this.requestDeleteMenu(accessToken);
                if (0 == wxMpResult.getErrcode()) {
                    log.info("重新删除菜单成功~");
                }else{
                    log.info("重新删除菜单失败，请联系系统管理人员");
                }
            }
        } else {
            log.info("删除菜单失败，请联系系统管理人员");
        }
        return wxMpResult;
    }
}

package org.jwechat.api.proxy.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jwechat.api.proxy.bean.menu.WxMpMenu;
import org.jwechat.api.proxy.bean.user.WxMpUserInfo;
import org.jwechat.api.proxy.enums.LangEnum;
import org.jwechat.api.proxy.service.WxMpUserService;
import org.jwechat.common.bean.WxMpResult;
import org.jwechat.common.util.WxHttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title WxMpUserServiceImpl
 * @Description 微信公众号用户管理服务
 * @Author ZhangKai
 * @Date 2020/4/8 0008
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@Service
public class WxMpUserServiceImpl implements WxMpUserService {

    @Autowired
    private RefreshAccessTokenServiceImpl refreshAccessTokenService;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public WxMpResult getWxMpUserInfo(String openId, LangEnum langEnum) {
        String accessToken = refreshAccessTokenService.getAccessToken();
        Map<String, Object> params = new HashMap<>();
        params.put("access_token",accessToken);
        params.put("openid",openId);
        params.put("lang",langEnum.getCode());
        String data = WxHttpUtil.httpGet(GET_USER_INFO_URL, params);
        JSONObject userInfo = JSONUtil.parseObj(data);
        if (StrUtil.isBlank(userInfo.getStr("openid"))) {
            return JSONUtil.toBean(userInfo, WxMpResult.class);
        }
        return WxMpResult.ok(userInfo);
    }
}

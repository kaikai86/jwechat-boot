package org.jwechat.common.bean.mp.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title Menu
 * @Description 自定义菜单
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@Getter
@Setter
public class WxMenuButton {

    /**
     * 必填 是 菜单的响应动作类型，
     * view表示网页类型，click表示点击类型，miniprogram表示小程序类型
     */
    @JsonProperty("type")
    private String type;

    /**
     * 必填 是 菜单标题，不超过16个字节，子菜单不超过60个字节
     */
    @JsonProperty("name")
    private String name;

    /**
     * 子菜单
     */
    @JsonProperty("sub_button")
    private List<WxMenuButton> subButtons = new ArrayList<WxMenuButton>();

    /**
     * 必填 是(view、miniprogram类型必须) 网页 链接，用户点击菜单可打开链接，不超过1024字节。
     */
    @JsonProperty("url")
    private String url;

    /**
     * 必填 是(media_id类型和view_limited类型必须)永久素材接口返回的合法media_id
     */
    @JsonProperty("media_id")
    private String mediaId;


    /**
     * 必填 是	(miniprogram类型必须)小程序的appid（仅认证公众号可配置）
     */
    @JsonProperty("appid")
    private String appid;

    /**
     * 必填 是	(miniprogram类型必须)小程序的页面路径
     */
    @JsonProperty("pagepath")
    private String pagepath;

    /**
     * 必填 是(click等点击类型必须) 菜单KEY值，用于消息接口推送，不超过128字节
     */
    @JsonProperty("key")
    private String key;
}

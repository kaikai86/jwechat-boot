package org.jwechat.api.proxy.bean.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Title WxMpUserInfo
 * @Description 用户管理-用户基本信息
 * @Author ZhangKai
 * @Date 2020/4/8 0008
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxMpUserInfo {

    /**
     * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     */
    @JsonProperty("subscribe")
    private Integer subscribe;
    /**
     * 用户的标识，对当前公众号唯一
     */
    @JsonProperty("openid")
    private String openid;
    /**
     * 用户的昵称
     */
    @JsonProperty("nickname")
    private String nickname;
    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    @JsonProperty("sex")
    private Integer sex;
    /**
     * 用户所在国家
     */
    @JsonProperty("country")
    private String country;
    /**
     * 用户所在省份
     */
    @JsonProperty("province")
    private String province;
    /**
     * 用户所在城市
     */
    @JsonProperty("city")
    private String city;
    /**
     * 用户的语言，简体中文为zh_CN
     */
    @JsonProperty("language")
    private String language;
    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。
     * 若用户更换头像，原有头像URL将失效。
     */
    @JsonProperty("headimgurl")
    private String headimgurl;
    /**
     * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     */
    @JsonProperty("subscribe_time")
    private Long subscribeTime;
    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     */
    @JsonProperty("unionid")
    private String unionid;
    /**
     * 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     */
    @JsonProperty("remark")
    private String remark;
    /**
     * 用户所在的分组ID（兼容旧的用户分组接口）
     */
    @JsonProperty("groupid")
    private Integer groupid;
    /**
     * 用户被打上的标签ID列表
     */
    @JsonProperty("tagid_list")
    private String[] tagidList;
    /**
     * 返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENE_PROFILE_ LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他
     */
    @JsonProperty("subscribe_scene")
    private String subscribeScene;
    /**
     * 二维码扫码场景（开发者自定义）
     */
    @JsonProperty("qr_scene")
    private String qrScene;
    /**
     * 二维码扫码场景描述（开发者自定义）
     */
    @JsonProperty("qr_scene_str")
    private String qrSceneStr;

}

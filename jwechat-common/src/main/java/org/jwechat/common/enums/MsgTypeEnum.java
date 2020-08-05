package org.jwechat.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Title MsgTypeEnum
 * @Description 消息类型枚举
 * @Author ZhangKai
 * @Date 2020/3/28 0028
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@Getter
@AllArgsConstructor
public enum MsgTypeEnum {

    /**
     * 消息类型，因为xml中将节点全部小些，所以需要注意一下
     */
    MsgType("msgtype", "消息"),
    /**
     * 菜单消息
     */
    MSGMENU("msgmenu", "菜单消息"),
    /**
     * 事件
     */
    EVENT("event", "事件消息"),
    /**
     * 文本消息
     */
    TEXT("text", "文本消息"),
    /**
     * 链接消息
     */
    LINK("link", "链接消息"),

    /**
     * 图片消息
     */
    IMAGE("image", "图片消息"),
    /**
     * 语音消息
     */
    VOICE("voice", "语音消息"),
    /**
     * 视频消息
     */
    VIDEO("video", "视频消息"),
    /**
     * 音乐消息
     */
    MUSIC("music", "音乐消息"),
    /**
     * 图文消息（点击跳转到外链）
     */
    NEWS("news", "图文消息（点击跳转到外链）"),
    /**
     * 图文消息（点击跳转到图文消息页面）
     */
    MPNEWS("mpnews", "图文消息（点击跳转到图文消息页面）"),
    /**
     * 地理消息
     */
    LOCATION("location", "地理消息"),
    /**
     * 小视频消息
     */
    SHORT_VIDEO("shortvideo", "小视频消息"),
    /**
     * 卡券
     */
    WXCARD("wxcard", "卡券消息"),
    /**
     * 小程序卡片
     */
    MINIPROGRAMPAGE("miniprogrampage", "小程序卡片消息");

    private String name;
    private String description;

    public static boolean containsMsgType(String typeStr) {
        MsgTypeEnum[] values = values();
        for (MsgTypeEnum msgTypeEnum : values) {
            if (msgTypeEnum.getName().equals(typeStr)) {
                return true;
            }
            ;
        }
        return false;
    }

    public static MsgTypeEnum getMsgTypeEnumByName(String msgType) {
        if (msgType == null || "".equals(msgType)) {
            return null;
        }
        MsgTypeEnum[] values = values();
        for (MsgTypeEnum msgTypeEnum : values) {
            if (msgTypeEnum.getName().equals(msgType)) {
                return msgTypeEnum;
            }
        }
        return null;
    }

}

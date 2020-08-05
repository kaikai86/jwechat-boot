package org.jwechat.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Title MsgSendTypeEnum
 * @Description 消息发送类型枚举
 * @Author ZhangKai
 * @Date 2020/3/28 0028
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@Getter
@AllArgsConstructor
public enum MsgSendTypeEnum {

    /**
     * 成员
     */
    USER("user", "成员"),
    /**
     * 部门
     */
    PART("party", "部门"),
    /**
     * 标签
     */
    TAG("tag", "标签");


    private String name;
    private String description;

    public static boolean containsMsgType(String typeStr) {
        MsgSendTypeEnum[] values = values();
        for (MsgSendTypeEnum msgTypeEnum : values) {
            if (msgTypeEnum.getName().equals(typeStr)) {
                return true;
            }
            ;
        }
        return false;
    }

    public static MsgSendTypeEnum getMsgTypeEnumByName(String msgType) {
        if (msgType == null || "".equals(msgType)) {
            return null;
        }
        MsgSendTypeEnum[] values = values();
        for (MsgSendTypeEnum msgTypeEnum : values) {
            if (msgTypeEnum.getName().equals(msgType)) {
                return msgTypeEnum;
            }
        }
        return null;
    }


}

package org.jwechat.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Title LangEnum
 * @Description 国家地区语言版本
 * @Author ZhangKai
 * @Date 2020/4/7 0007
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@Getter
@AllArgsConstructor
public enum FileTypeEnum {
    IMAGE("image","图片"),
    VOICE("voice","语音"),
    VIDEO("video", "视频"),
    FILE("file", "普通文件");

    private String code;
    private String desc;


}


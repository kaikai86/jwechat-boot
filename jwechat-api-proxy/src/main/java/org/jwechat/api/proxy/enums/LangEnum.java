package org.jwechat.api.proxy.enums;

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
public enum LangEnum {
    ZH_CN("zh_CN","简体"),
    ZH_TW("zh_TW","繁体"),
    EN("en", "英语");

    private String code;
    private String desc;


}

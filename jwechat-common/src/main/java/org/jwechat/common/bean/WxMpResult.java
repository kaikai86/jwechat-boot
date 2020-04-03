package org.jwechat.common.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @Title WxMpResult
 * @Description 微信公众号返回结果实体类
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@Setter
@Getter
public class WxMpResult {

    private Integer errcode;
    private String errmsg;
}

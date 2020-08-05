package org.jwechat.common.bean.common;

import lombok.*;

/**
 * @Title WxCorpResult
 * @Description 企业微信返回结果实体类
 * @Author ZhangKai
 * @Date 2020/4/2 0002
 * @Version 1.0
 * @Email 410618538@qq.com
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCorpResult {

    private Integer errcode;
    private String errmsg;

    public static WxCorpResult ok() {
        return WxCorpResult.builder().errcode(0).errmsg("success!").build();
    }

    public static WxCorpResult ok(String errmsg) {
        return WxCorpResult.builder().errcode(0).errmsg(errmsg).build();
    }

}

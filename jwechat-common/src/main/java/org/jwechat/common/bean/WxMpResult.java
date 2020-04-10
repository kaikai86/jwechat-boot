package org.jwechat.common.bean;

import cn.hutool.json.JSONObject;
import lombok.*;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMpResult {

    private Integer errcode;
    private String errmsg;
    private JSONObject data;

    public static WxMpResult ok() {
        return WxMpResult.builder().errcode(0).errmsg("success!").build();
    }

    public static WxMpResult ok(String errmsg) {
        return WxMpResult.builder().errcode(0).errmsg(errmsg).build();
    }

    public static WxMpResult ok(JSONObject data) {
        return WxMpResult.builder().errcode(0).errmsg("success!").data(data).build();
    }

}

package org.jwechat.common.bean.common;

import cn.hutool.json.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WxCorpOAApproveDetailResult extends WxCorpResult {

    private JSONObject info;
}

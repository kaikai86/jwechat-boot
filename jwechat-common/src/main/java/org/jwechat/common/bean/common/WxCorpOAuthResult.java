package org.jwechat.common.bean.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxCorpOAuthResult extends WxCorpResult {

    private String UserId;
    private String OpenId;
    private String DeviceId;
}

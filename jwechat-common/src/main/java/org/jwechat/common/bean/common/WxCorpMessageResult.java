package org.jwechat.common.bean.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxCorpMessageResult extends WxCorpResult {

    private String invaliduser;
    private String invalidparty;
    private String invalidtag;
}

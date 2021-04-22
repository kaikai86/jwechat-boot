package org.jwechat.common.bean.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxCorpMediaResult extends WxCorpResult {

    private String media_id;
    private String created_at;
    private String type;
}

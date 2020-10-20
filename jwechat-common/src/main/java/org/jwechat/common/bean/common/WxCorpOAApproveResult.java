package org.jwechat.common.bean.common;

import lombok.Getter;
import lombok.Setter;
import org.jwechat.common.bean.corp.oa.WxCorpOAClockInInfo;

import java.util.List;

@Getter
@Setter
public class WxCorpOAApproveResult extends WxCorpResult {

    private List<String> sp_no_list;
}

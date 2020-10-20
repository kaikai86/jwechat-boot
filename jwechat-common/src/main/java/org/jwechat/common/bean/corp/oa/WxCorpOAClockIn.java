package org.jwechat.common.bean.corp.oa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class WxCorpOAClockIn {

    //打卡类型。1：上下班打卡；2：外出打卡；3：全部打卡
    @JsonProperty("opencheckindatatype")
    private String  opencheckindatatype;

    //获取打卡记录的开始时间。Unix时间戳
    @JsonProperty("starttime")
    private Long  starttime;

    //获取打卡记录的结束时间。Unix时间戳
    //获取记录时间跨度不超过30天
    @JsonProperty("endtime")
    private Long  endtime;

    //需要获取打卡记录的用户列表
    //用户列表不超过100个。若用户超过100个，请分批获取
    @JsonProperty("useridlist")
    private List<String> useridlist;

}

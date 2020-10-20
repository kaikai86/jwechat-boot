package org.jwechat.common.bean.corp.oa;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
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
public class WxCorpOAApprove {

    //分页查询游标，默认为0，后续使用返回的next_cursor进行分页拉取
    @JsonProperty("cursor")
    private int  cursor;

    //一次请求拉取审批单数量，默认值为100，上限值为100
    @JsonProperty("size")
    private int  size;

    //获取审批记录的开始时间。Unix时间戳
    @JsonProperty("starttime")
    private Long  starttime;

    //获取审批记录的结束时间。Unix时间戳
    //获取记录时间跨度不超过31天
    @JsonProperty("endtime")
    private Long  endtime;

    //需要获取打卡记录的用户列表
    //用户列表不超过100个。若用户超过100个，请分批获取
    @JsonProperty("filters")
    private JSONArray filters;

}

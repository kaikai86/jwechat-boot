package org.jwechat.common.bean.corp.oa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class WxCorpOAClockInInfo {

    //用户id
    @JsonProperty("userid")
    private String  userid;

    //打卡规则名称
    @JsonProperty("groupname")
    private String  groupname;

    //打卡类型。字符串，目前有：上班打卡，下班打卡，外出打卡
    @JsonProperty("checkin_type")
    private String  checkinType;

    //异常类型，字符串，包括：时间异常，地点异常，未打卡，wifi异常，非常用设备。如果有多个异常，以分号间隔
    @JsonProperty("exception_type")
    private String  exceptionType;

    //打卡时间。Unix时间戳
    @JsonProperty("checkin_time")
    private Long  checkinTime;

    //打卡地点title
    @JsonProperty("location_title")
    private String  locationTitle;

    //打卡地点详情
    @JsonProperty("location_detail")
    private String  locationDetail;

    //打卡wifi名称
    @JsonProperty("wifiname")
    private String  wifiname;

    //打卡备注
    @JsonProperty("notes")
    private String  notes;

    //打卡的MAC地址/bssid
    @JsonProperty("wifimac")
    private String  wifimac;

    //打卡的附件media_id，可使用media/get获取附件
    @JsonProperty("mediaids")
    private String  mediaids;

    //位置打卡地点纬度，是实际纬度的1000000倍，与腾讯地图一致采用GCJ-02坐标系统标准
    @JsonProperty("lat")
    private String lat;

    //位置打卡地点经度，是实际经度的1000000倍，与腾讯地图一致采用GCJ-02坐标系统标准
    @JsonProperty("lng")
    private String lng;

    //打卡设备id
    @JsonProperty("deviceid")
    private String  deviceid;

}

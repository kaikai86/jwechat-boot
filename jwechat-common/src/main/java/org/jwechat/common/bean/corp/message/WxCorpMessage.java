package org.jwechat.common.bean.corp.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jwechat.common.enums.MsgSendTypeEnum;
import org.jwechat.common.enums.MsgTypeEnum;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class WxCorpMessage {
    //指定接收消息的成员，成员ID列表（多个接收者用‘|’分隔，最多支持1000个）。
    //特殊情况：指定为”@all”，则向该企业应用的全部成员发送
    @JsonProperty("touser")
    private String  touser;

    //指定接收消息的部门，部门ID列表，多个接收者用‘|’分隔，最多支持100个。
    //当touser为”@all”时忽略本参数
    @JsonProperty("toparty")
    private String  toparty;

    //指定接收消息的标签，标签ID列表，多个接收者用‘|’分隔，最多支持100个。
    //当touser为”@all”时忽略本参数
    @JsonProperty("totag")
    private String  totag;

    //企业应用的id，整型。企业内部开发，可在应用的设置页面查看；第三方服务商，可通过接口 获取企业授权信息 获取该参数值
    @JsonProperty("agentid")
    private String  agentid;

    //表示是否是保密消息，0表示否，1表示是，默认0
    @JsonProperty("safe")
    private Integer safe;

    //表示是否开启重复消息检查，0表示否，1表示是，默认0
    @JsonProperty("enable_duplicate_check")
    private Integer enableDuplicateCheck;

    //表示是否重复消息检查的时间间隔，默认1800s，最大不超过4小时
    @JsonProperty("duplicate_check_interval")
    private Integer duplicateCheckInterval;

    //消息类型,根据不同类型有不同值
    @JsonProperty("msgtype")
    private String msgType;

    private WxCorpMessage(MsgTypeEnum msgType, String agentid) {
        this.msgType = msgType.getName();
        this.agentid = agentid;
    }

    public WxCorpMessage(String agentid, MsgTypeEnum msgType, MsgSendTypeEnum msgSendTypeEnum, String sendTypeValue) {
        this(msgType,agentid);
        switch (msgSendTypeEnum) {
            case USER:
                this.touser = sendTypeValue;
            case PART:
                this.toparty = sendTypeValue;
            case TAG:
                this.totag = sendTypeValue;
            default:
                this.touser = sendTypeValue;
        }
    }

    public WxCorpMessage(String agentid,MsgTypeEnum msgType, String touser) {
        this(agentid, msgType, touser, null, null);
    }

    public WxCorpMessage(String agentid,MsgTypeEnum msgType, String touser,String toparty) {
        this(agentid, msgType, touser, toparty, null);
    }

    public WxCorpMessage(String agentid,MsgTypeEnum msgType, String touser,String toparty,String totag) {
        this(msgType,agentid);
        this.touser = touser;
        this.toparty = toparty;
        this.totag = totag;
    }
}

package org.jwechat.common.bean.corp.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jwechat.common.enums.MsgSendTypeEnum;
import org.jwechat.common.enums.MsgTypeEnum;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class WxCorpTextMessage extends WxCorpMessage{

    @JsonProperty("text")
    private Text text;

    public WxCorpTextMessage(String agentid, MsgTypeEnum msgType, MsgSendTypeEnum msgSendTypeEnum, String sendTypeValue,Text text) {
        super(agentid, msgType, msgSendTypeEnum, sendTypeValue);
        this.text = text;
    }

    public WxCorpTextMessage(String agentid, MsgTypeEnum msgType, String touser,Text text) {
        this(agentid, msgType, touser,null,null,text);
    }

    public WxCorpTextMessage(String agentid, MsgTypeEnum msgType, String touser, String toparty,Text text) {
        this(agentid, msgType, touser,toparty,null,text);
    }

    public WxCorpTextMessage(String agentid, MsgTypeEnum msgType, String touser, String toparty, String totag,Text text) {
        super(agentid, msgType, touser, toparty, totag);
        this.text = text;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Text{
        @JsonProperty("content")
        private String content;
    }

}

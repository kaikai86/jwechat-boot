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
public class WxCorpFileMessage extends WxCorpMessage{

    @JsonProperty("file")
    private File file;

    public WxCorpFileMessage(String agentid, MsgTypeEnum msgType, MsgSendTypeEnum msgSendTypeEnum, String sendTypeValue, File file) {
        super(agentid, msgType, msgSendTypeEnum, sendTypeValue);
        this.file = file;
    }

    public WxCorpFileMessage(String agentid, MsgTypeEnum msgType, String touser, File file) {
        this(agentid, msgType, touser,null,null,file);
    }

    public WxCorpFileMessage(String agentid, MsgTypeEnum msgType, String touser, String toparty, File file) {
        this(agentid, msgType, touser,toparty,null,file);
    }

    public WxCorpFileMessage(String agentid, MsgTypeEnum msgType, String touser, String toparty, String totag, File file) {
        super(agentid, msgType, touser, toparty, totag);
        this.file = file;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class File{
        @JsonProperty("media_id")
        private String mediaId;
    }

}

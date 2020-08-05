package org.jwechat.common.bean.corp.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jwechat.common.bean.corp.message.vo.Btn;
import org.jwechat.common.enums.MsgSendTypeEnum;
import org.jwechat.common.enums.MsgTypeEnum;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class WxCorpTaskCardMessage extends WxCorpMessage{

    @JsonProperty("taskcard")
    private TaskCard taskCard;

    public WxCorpTaskCardMessage(String agentid, MsgTypeEnum msgType, MsgSendTypeEnum msgSendTypeEnum, String sendTypeValue, TaskCard taskCard) {
        super(agentid, msgType, msgSendTypeEnum, sendTypeValue);
        this.taskCard = taskCard;
    }

    public WxCorpTaskCardMessage(String agentid, MsgTypeEnum msgType, String touser, TaskCard taskCard) {
        this(agentid, msgType, touser,null,null, taskCard);
    }

    public WxCorpTaskCardMessage(String agentid, MsgTypeEnum msgType, String touser, String toparty, TaskCard taskCard) {
        this(agentid, msgType, touser,toparty,null, taskCard);
    }

    public WxCorpTaskCardMessage(String agentid, MsgTypeEnum msgType, String touser, String toparty, String totag, TaskCard taskCard) {
        super(agentid, msgType, touser, toparty, totag);
        this.taskCard = taskCard;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TaskCard{
        @JsonProperty("title")
        private String title;
        @JsonProperty("description")
        private String description;
        @JsonProperty("url")
        private String url;
        @JsonProperty("task_id")
        private String taskId;
        @JsonProperty("btn")
        private List<Btn> btns;
    }

}

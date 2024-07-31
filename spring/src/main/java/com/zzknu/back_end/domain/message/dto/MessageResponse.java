package com.zzknu.back_end.domain.message.dto;

import com.zzknu.back_end.domain.message.entity.Message;
import com.zzknu.back_end.domain.quote.dto.MessageQuoteDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageResponse {
    private Long sender_id;
    private Long receiver_id;
    private String sender_nickName;
    private String receiver_nickName;
    private String message_title;
    private MessageQuoteDto messageQuote;
    private LocalDateTime createdAt;

    public MessageResponse(Message message) {
        this.sender_id = message.getSendUser().getId();
        this.receiver_id = message.getRecvUser().getId();
        this.sender_nickName = message.getSendUser().getNickName();
        this.receiver_nickName = message.getRecvUser().getNickName();
        this.message_title = message.getTitle();
        this.messageQuote = new  MessageQuoteDto(message.getQuote());
        this.createdAt = message.getCreatedAt();
    }
}

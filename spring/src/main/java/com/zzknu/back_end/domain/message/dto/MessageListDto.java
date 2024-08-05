package com.zzknu.back_end.domain.message.dto;

import com.zzknu.back_end.domain.message.entity.Message;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class MessageListDto {
    private Long id;
    private Long sender_id;
    private Long receiver_id;
    private String sender_nickName;
    private String receiver_nickName;
    private String message_title;
    private LocalDateTime createdAt;
    private boolean isRead;

    public MessageListDto(Message message) {
        this.id = message.getId();
        this.sender_id = message.getSendUser().getId();
        this.receiver_id = message.getRecvUser().getId();
        this.sender_nickName = message.getSendUser().getNickname();
        this.receiver_nickName = message.getRecvUser().getNickname();
        this.message_title = message.getTitle();
        this.createdAt = message.getCreatedAt();
        this.isRead = message.getIsRead();
    }
}

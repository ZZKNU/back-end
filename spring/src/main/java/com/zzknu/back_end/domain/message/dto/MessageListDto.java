package com.zzknu.back_end.domain.message.dto;

import com.zzknu.back_end.domain.message.entity.Message;

import java.time.LocalDateTime;

public class MessageListDto {
    private Long sender_id;
    private Long receiver_id;
    private String sender_nickName;
    private String receiver_nickName;
    private String message_title;
    private LocalDateTime createdAt;
    private boolean isRead;

    public MessageListDto(Message message) {
        this.sender_id = message.getSend_user().getId();
        this.receiver_id = message.getRecv_user().getId();
        this.sender_nickName = message.getSend_user().getNickName();
        this.receiver_nickName = message.getRecv_user().getNickName();
        this.message_title = message.getTitle();
        this.createdAt = message.getCreatedAt();
        this.isRead = message.getIsRead();
    }
}

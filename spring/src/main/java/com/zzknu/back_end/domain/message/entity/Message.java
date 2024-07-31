package com.zzknu.back_end.domain.message.entity;

import com.zzknu.back_end.domain.quote.entity.Quote;
import com.zzknu.back_end.domain.user.entity.User;
import com.zzknu.back_end.global.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Message extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    // 1. 보내는 User 와 N:1 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sendUser")
    private User sendUser;

    // 1-1. 받는 User 와 N:1 매핑
    @ManyToOne
    @JoinColumn(name = "recvUser")
    private User recvUser;

    // 2. Quote 와 N:1 매핑
    @ManyToOne
    @JoinColumn(name = "quote_id")
    private Quote quote;
    // Builder toEntity
    // 많은 고민이 필요하겠군

    private String title;

    private Boolean isRead = false;

    public Message(User sendUser, User toUser, String title, Quote quote) {
        this.sendUser = sendUser;
        this.recvUser = toUser;
        this.title = title;
        this.quote = quote;
    }

    public void setIsRead() {
        this.isRead = true;
    }
}

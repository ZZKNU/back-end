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
    private Long toId;

    // 1. 보내는 User 와 N:1 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "send_user")
    private User send_user;

    // 1-1. 받는 User 와 N:1 매핑
    @ManyToOne
    @JoinColumn(name = "recv_user")
    private User recv_user;

    // 2. Quote 와 N:1 매핑
    @ManyToOne
    @JoinColumn(name = "quote_id")
    private Quote quote;
    // Builder toEntity
    // 많은 고민이 필요하겠군
}

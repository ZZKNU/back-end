package com.zzknu.back_end.domain.likedquote.entity;

import com.zzknu.back_end.domain.quote.entity.Quote;
import com.zzknu.back_end.domain.user.entity.User;
import com.zzknu.back_end.global.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class LikedQuote extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likedquote_id")
    private Long id;
    // User 와 Quote N:M 관계의 중간 테이블 역할.

    // 1. User 와 N:1 매핑
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 2. Quote 와 N:1 매핑
    @ManyToOne
    @JoinColumn(name = "quote_id")
    private Quote quote;

    // 흠 얘도 Builder 필요한가? toEntity도... 아마 필요없을 것 같은데
}

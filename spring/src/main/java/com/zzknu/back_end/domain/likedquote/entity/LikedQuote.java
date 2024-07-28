package com.zzknu.back_end.domain.likedquote.entity;

import com.zzknu.back_end.global.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class LikedQuote extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // User 와 Quote N:M 관계의 중간 테이블 역할.
    // 흠 얘도 Builder 필요한가? toEntity도... 아마 필요없을 것 같은데
}

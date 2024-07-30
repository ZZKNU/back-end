package com.zzknu.back_end.domain.myfortune.entity;

import com.zzknu.back_end.domain.user.entity.User;
import com.zzknu.back_end.global.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class MyFortune extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "myfortune_id")
    private Long id;
    private String fortune;

    // 1. User 와 1 : N 매핑
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 역시 builder, toEntity 함수 제작 여부
    // 얘는 API 도 따와야해서...
}

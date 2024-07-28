package com.zzknu.back_end.domain.myfortune.entity;

import com.zzknu.back_end.global.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class MyFortune extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fortune;

    // User : fortune = 1 : N

    // 역시 builder, toEntity 함수 제작 여부
    // 얘는 API 도 따와야해서...
}

package com.zzknu.back_end.domain.message.entity;

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
public class Message extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long toId;

    // User 와 Quote 사이읭 N:M 관계의 중간 테이블 역할

    // Builder toEntity
    // 많은 고민이 필요하겠군
}

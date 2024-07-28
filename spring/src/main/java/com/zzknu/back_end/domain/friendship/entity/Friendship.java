package com.zzknu.back_end.domain.friendship.entity;

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
public class Friendship extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long friendId;
    // User와 N:1 관계
    // toEntity, Builder 패턴 추가 여부
}

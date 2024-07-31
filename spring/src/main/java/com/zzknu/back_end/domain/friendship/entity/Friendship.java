package com.zzknu.back_end.domain.friendship.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.zzknu.back_end.domain.user.entity.User;
import com.zzknu.back_end.global.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Friendship extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friendship_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // Friendship이 여러 개일 수 있는 User와의 관계
    @JoinColumn(name = "from_user", nullable = false) // from_user를 외래 키로 설정
    @JsonManagedReference
    private User from_user; // 친구 추가 건 사람

    @ManyToOne // 여러 개의 Friendship에 연결된 User
    @JoinColumn(name = "to_user", nullable = false) // to_user를 외래 키로 설정
    @JsonManagedReference
    private User to_user; // 친구 추가 받은 사람

    public Friendship(User fromUser, User toUser) {
        this.from_user = fromUser;
        this.to_user = toUser;
    }

    // User와 N:1 관계
    // toEntity, Builder 패턴 추가 여부
}

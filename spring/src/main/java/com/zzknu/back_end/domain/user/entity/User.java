package com.zzknu.back_end.domain.user.entity;

import com.zzknu.back_end.domain.friendship.entity.Friendship;
import com.zzknu.back_end.domain.user.entity.type.AuthorityType;
import com.zzknu.back_end.global.BaseEntityWithUpdatedAt;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseEntityWithUpdatedAt {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String nickName;

    private Date birthDate;

    private AuthorityType authority;

    @OneToMany(mappedBy = "from_user") // Friendship에서 fromUser로 매핑
    private List<Friendship> friendships; // 친구 추가 건

    // 많은 연관 관계

    // builder, toEntity
}

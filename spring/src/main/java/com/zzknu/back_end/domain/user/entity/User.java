package com.zzknu.back_end.domain.user.entity;

import com.zzknu.back_end.domain.friendship.entity.Friendship;
import com.zzknu.back_end.domain.likedquote.entity.LikedQuote;
import com.zzknu.back_end.domain.message.entity.Message;
import com.zzknu.back_end.domain.myfortune.entity.MyFortune;
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


    // 1. Friendship 과 1:N 매핑
    @OneToMany(mappedBy = "from_user") // Friendship에서 fromUser로 매핑
    private List<Friendship> friendships; // 친구 추가 건

    // 2. LikedQuote 와 1:N 매핑
    @OneToMany(mappedBy = "user")
    private List<LikedQuote> likedQuotes;

    // 3. Myfortune 와 1:N 매핑
    @OneToMany(mappedBy = "user")
    private List<MyFortune> myFortunes;

    // 4. Message 와 1:N 매핑 -> friendship 보고 따라했음
    @OneToMany(mappedBy = "send_user")
    private List<Message> messages;

    // builder, toEntity
}

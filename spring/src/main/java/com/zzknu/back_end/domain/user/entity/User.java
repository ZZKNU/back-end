package com.zzknu.back_end.domain.user.entity;

import com.zzknu.back_end.domain.user.entity.type.AuthorityType;
import com.zzknu.back_end.global.BaseEntityWithUpdatedAt;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
public class User extends BaseEntityWithUpdatedAt {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String nickName;
    private Date birthDate;
    private AuthorityType authority;

    // 많은 연관 관계

    // builder, toEntity
}

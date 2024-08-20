package com.zzknu.back_end.domain.user.dto;

import com.zzknu.back_end.domain.user.entity.User;
import com.zzknu.back_end.domain.user.entity.type.AuthorityType;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserInfoDto {
    private final Long id;
    private final String email;
    private final String nickname;
    private final LocalDate birthdate;
    private final AuthorityType authority;

    public UserInfoDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.birthdate = user.getBirthdate();
        this.authority = user.getAuthority();
    }
}

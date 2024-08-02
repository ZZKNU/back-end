package com.zzknu.back_end.domain.user.dto;

import com.zzknu.back_end.domain.user.entity.User;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserInfoDto {
    private String email;
    private String nickname;
    private LocalDate birthdate;

    public UserInfoDto(User user) {
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.birthdate = user.getBirthdate();
    }
}

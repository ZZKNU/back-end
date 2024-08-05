package com.zzknu.back_end.domain.admin.dto;

import com.zzknu.back_end.domain.user.entity.User;
import com.zzknu.back_end.domain.user.entity.type.AuthorityType;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserResponse {
    private Long id;
    private String email;
    private String nickname;
    private LocalDate birthdate;
    private AuthorityType authority;

    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.birthdate = user.getBirthdate();
        this.authority = user.getAuthority();
    }
}

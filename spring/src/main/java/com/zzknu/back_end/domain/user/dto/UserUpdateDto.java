package com.zzknu.back_end.domain.user.dto;

import com.zzknu.back_end.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class UserUpdateDto {
    private String nickName;
    private Date birthDate;

    public void update(User existingUser) {
        existingUser.setNickname(this.nickName);
        existingUser.setBirthdate(this.birthDate);
    }
}

package com.zzknu.back_end.domain.user.dto;

import com.zzknu.back_end.domain.user.entity.User;

import java.util.Date;

public class UserUpdateDto {
    private String nickName;
    private Date birthDate;

    public void update(User existingUser) {
        existingUser.setNickName(this.nickName);
        existingUser.setBirthDate(this.birthDate);
    }
}

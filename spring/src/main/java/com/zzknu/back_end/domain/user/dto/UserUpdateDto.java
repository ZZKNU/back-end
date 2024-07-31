package com.zzknu.back_end.domain.user.dto;

import com.zzknu.back_end.domain.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class UserUpdateDto {
    private String nickName;
    private Date birthDate;

    public void update(User existingUser) {
        existingUser.setNickName(this.nickName);
        existingUser.setBirthDate(this.birthDate);
    }
}

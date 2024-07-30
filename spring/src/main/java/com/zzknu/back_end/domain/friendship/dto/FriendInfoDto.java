package com.zzknu.back_end.domain.friendship.dto;

import com.zzknu.back_end.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class FriendInfoDto {
    private Long id;
    private String email;
    private String nickName;
    private Date birthDate;

    public FriendInfoDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickName = user.getNickName();
        this.birthDate = user.getBirthDate();
    }
}

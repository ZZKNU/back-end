package com.zzknu.back_end.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginRequestDto {
    private String email;
    private String password;
}

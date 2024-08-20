package com.zzknu.back_end.domain.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FindPasswordDto {
    @NotNull(message = "이름은 필수입니다.")
    private String name;

    @NotNull(message = "이메일은 필수입니다.")
    private String email;

    @NotNull(message = "휴대폰번호는 필수입니다.")
    private String phone;
}

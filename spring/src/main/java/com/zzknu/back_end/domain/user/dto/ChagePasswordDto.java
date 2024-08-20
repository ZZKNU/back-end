package com.zzknu.back_end.domain.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ChagePasswordDto {
    @NotNull(message = "이메일은 필수입니다.")
    private String email;

    @NotNull(message = "기존 비밀번호는 필수 입니다.")
    private String oldPassword;

    @NotNull(message = "새 비밀번호는 필수 입니다.")
    private String newPassword;
}

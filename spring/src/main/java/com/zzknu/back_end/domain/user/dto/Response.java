package com.zzknu.back_end.domain.user.dto;

import lombok.Getter;

@Getter
public class Response {
    private final String accessToken;

    public Response(String accessToken) {
        this.accessToken = accessToken;
    }
}

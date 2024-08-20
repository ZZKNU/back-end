package com.zzknu.back_end.domain.quote.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseSuccessful {
    private Boolean success;
    private String log;
}

package com.zzknu.back_end.domain.category.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseWithLog {
    private boolean success;
    private String log;
}

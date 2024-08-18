package com.zzknu.back_end.domain.quote.dto;

import com.zzknu.back_end.domain.quote.entity.type.QuoteType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class QuoteRequestDto {
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private QuoteType quoteType;
    private String category;
    private String content;
    private String author;
    // Category에 대한 생각?
}

package com.zzknu.back_end.domain.quote.dto;

import com.zzknu.back_end.domain.quote.entity.Quote;

public class MessageQuoteDto {
    private Long quote_id;
    private String quote_title;
    private String quote_content;
    private String quote_author;

    public MessageQuoteDto(Quote quote) {
        this.quote_id = quote.getId();
        this.quote_title = quote.getTitle();
        this.quote_content = quote.getContent();
        this.quote_author = quote.getAuthor();
    }
}

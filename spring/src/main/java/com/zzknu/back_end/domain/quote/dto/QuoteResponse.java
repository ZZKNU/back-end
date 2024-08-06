package com.zzknu.back_end.domain.quote.dto;

import com.zzknu.back_end.domain.quote.entity.Quote;
import com.zzknu.back_end.domain.quote.entity.type.QuoteType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteResponse {
    private final Long id;

    private final String title;

    private final QuoteType type;

    private final String content;

    private final String author;

    private final Boolean certified ;

    private final int liked;

    private Boolean isLiked;

    private String nickname;

    public QuoteResponse(Quote quote) {
        this.id = quote.getId();
        this.title = quote.getTitle();
        this.type = quote.getType();
        this.content = quote.getContent();
        this.author = quote.getAuthor();
        this.certified = quote.getCertified();
        this.liked = quote.getLiked();
        this.nickname = quote.getUser().getNickname();
    }

    public QuoteResponse(Quote quote, boolean isLiked) {
        this.id = quote.getId();
        this.title = quote.getTitle();
        this.type = quote.getType();
        this.content = quote.getContent();
        this.author = quote.getAuthor();
        this.certified = quote.getCertified();
        this.liked = quote.getLiked();
        this.isLiked = isLiked;
        this.nickname = quote.getUser().getNickname();
    }
}

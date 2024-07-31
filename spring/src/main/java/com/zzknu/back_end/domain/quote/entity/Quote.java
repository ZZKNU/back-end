package com.zzknu.back_end.domain.quote.entity;

import com.zzknu.back_end.domain.category.entity.Category;
import com.zzknu.back_end.domain.likedquote.entity.LikedQuote;
import com.zzknu.back_end.domain.message.entity.Message;
import com.zzknu.back_end.domain.quote.dto.QuoteRequestDto;
import com.zzknu.back_end.domain.quote.dto.QuoteUpdateRequestDto;
import com.zzknu.back_end.domain.quote.entity.type.CertifiedType;
import com.zzknu.back_end.domain.quote.entity.type.QuoteType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Quote {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quote_id")
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private QuoteType type;

    private String content;

    private String author;

    @Enumerated(EnumType.STRING)
    private CertifiedType certified;

    private int liked = 0;

    // 1. Category와 N:1 매핑
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // 2. LikedQuote 와 1:N 매핑
    @OneToMany(mappedBy = "quote")
    private List<LikedQuote> likedQuotes;

    // 3. Message 와 1:N 매핑
    @OneToMany(mappedBy = "quote")
    private List<Message> messageList;

    public void increaseLikes() {
        this.liked += 1;
    }

    public void update(QuoteUpdateRequestDto quoteUpdateRequestDto) {
        this.title = quoteUpdateRequestDto.getTitle();
        this.content = quoteUpdateRequestDto.getContent();
        this.author = quoteUpdateRequestDto.getAuthor();
        this.type = quoteUpdateRequestDto.getQuoteType();
    }

    public static Quote toEntity(QuoteRequestDto quoteRequestDto) {
        return Quote.builder().title(quoteRequestDto.getTitle())
                .content(quoteRequestDto.getContent())
                .author(quoteRequestDto.getAuthor())
                .quoteType(quoteRequestDto.getQuoteType())
                .build();
    }

    @Builder
    public Quote(String title, QuoteType quoteType, String content, String author, Category category) {
        this.title = title;
        this.type = quoteType;
        this.content = content;
        this.author = author;
        this.category = category;
    }
}

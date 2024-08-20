package com.zzknu.back_end.domain.category.entity;

import com.zzknu.back_end.domain.quote.entity.Quote;
import com.zzknu.back_end.global.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Category extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;
    private String categoryName;
    // 예시 - 사랑 우정 행복 희망 격려


    // quote와 1:N 관계
    @OneToMany(mappedBy = "category")
    private List<Quote> quotes;

    @Builder
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
    // toEntity 추가 여부

    public void addQuote(Quote quote) {
        this.quotes.add(quote);
    }
}

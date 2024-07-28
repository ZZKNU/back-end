package com.zzknu.back_end.domain.quote.entity;

import com.zzknu.back_end.domain.quote.entity.type.CertifiedType;
import com.zzknu.back_end.domain.quote.entity.type.QuoteType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Quote {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private QuoteType type;
    private String content;
    private String author;
    private CertifiedType certified;
    private Long liked;


}

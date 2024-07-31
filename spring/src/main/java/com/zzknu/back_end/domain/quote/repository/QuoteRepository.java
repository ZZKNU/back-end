package com.zzknu.back_end.domain.quote.repository;

import com.zzknu.back_end.domain.quote.entity.Quote;
import com.zzknu.back_end.domain.quote.entity.type.CertifiedType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    List<Quote> findByCertified(CertifiedType certifiedType);
    List<Quote> findByAuthor(String author);
}

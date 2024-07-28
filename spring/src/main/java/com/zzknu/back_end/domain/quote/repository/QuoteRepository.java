package com.zzknu.back_end.domain.quote.repository;

import com.zzknu.back_end.domain.quote.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
}

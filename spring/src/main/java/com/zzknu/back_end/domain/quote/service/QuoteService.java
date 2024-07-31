package com.zzknu.back_end.domain.quote.service;

import com.zzknu.back_end.domain.quote.entity.Quote;
import com.zzknu.back_end.domain.quote.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuoteService {
    private final QuoteRepository quoteRepository;

    public Quote findById(Long quoteId) {
        return quoteRepository.findById(quoteId)
                .orElseThrow(() -> new RuntimeException("Quote not found"));
    }
}

package com.zzknu.back_end.domain.quote.controller;

import com.zzknu.back_end.domain.quote.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuoteController {
    private final QuoteService quoteService;
}

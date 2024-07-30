package com.zzknu.back_end.domain.quote.controller;

import com.zzknu.back_end.domain.quote.dto.QuoteRequestDto;
import com.zzknu.back_end.domain.quote.dto.QuoteUpdateRequestDto;
import com.zzknu.back_end.domain.quote.entity.Quote;
import com.zzknu.back_end.domain.quote.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuoteController {
    private final QuoteService quoteService;

    @PostMapping("/challenges")
    public Quote createQuote(@RequestBody QuoteRequestDto quoteRequestDto) {
        return quoteService.createQuote(quoteRequestDto);
    }

    @PutMapping("/challenges")
    public Quote updateQuote(@RequestBody QuoteUpdateRequestDto quoteUpdateRequestDto) {
        return quoteService.updateQuote(quoteUpdateRequestDto);
    }

    @GetMapping("/challenges")
    public List<Quote> getWaitQuotes(){
        return quoteService.getWaitQuotes();
    }

    @GetMapping("/quotes")
    public List<Quote> getAcceptedQuotes(){
        return quoteService.getAcceptedQuotes();
    }

    // 맞는지 모르겠음
    @PutMapping({"/challenges", "/quotes"})
    public void increaseLikes(@RequestBody Long id){
        quoteService.increaseLikes(id);
    }

    @GetMapping({"/challenges", "/quotes"})
    public Quote getQuoteById(@RequestParam("id") Long id) {
        return quoteService.getQuoteById(id);
    }

    @DeleteMapping("/challenges")
    public void deleteQuoteById(@RequestParam("id") Long id) {
        quoteService.deleteQuoteById(id);
    }
}

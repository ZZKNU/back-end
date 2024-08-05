package com.zzknu.back_end.domain.quote.controller;

import com.zzknu.back_end.domain.quote.dto.QuoteRequestDto;
import com.zzknu.back_end.domain.quote.dto.QuoteResponse;
import com.zzknu.back_end.domain.quote.dto.QuoteUpdateRequestDto;
import com.zzknu.back_end.domain.quote.dto.ResponseSuccessful;
import com.zzknu.back_end.domain.quote.entity.Quote;
import com.zzknu.back_end.domain.quote.service.QuoteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuoteController {
    private final QuoteService quoteService;

    @Operation(summary = "글귀 작성")
    @PostMapping("/challenges")
    public ResponseEntity<ResponseSuccessful> createQuote(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken, @RequestBody QuoteRequestDto quoteRequestDto) {
        return ResponseEntity.ok(quoteService.createQuote(accessToken, quoteRequestDto));
    }

    @Operation(summary = "작성 글귀 수정")
    @PutMapping("/challenges")
    public ResponseEntity<ResponseSuccessful> updateQuote(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken, @RequestBody QuoteUpdateRequestDto quoteUpdateRequestDto) {
        return ResponseEntity.ok(quoteService.updateQuote(accessToken, quoteUpdateRequestDto));
    }

    @Operation(summary = "베스트 도전 글귀 불러오기")
    @GetMapping("/challenges")
    public ResponseEntity<List<QuoteResponse>> getWaitQuotes(Pageable pageable){
        Page<QuoteResponse> quotes = quoteService.getWaitQuotes(pageable);
        return ResponseEntity.ok(quotes.getContent());
    }

    @Operation(summary = "검증된 글귀 불러오기 (모든 글귀 열람)")
    @GetMapping("/quotes")
    public ResponseEntity<List<QuoteResponse>> getAcceptedQuotes(Pageable pageable){
        Page<QuoteResponse> quotes = quoteService.getAcceptedQuotes(pageable);
        return ResponseEntity.ok(quotes.getContent());
    }

    @Operation(summary = "글귀 좋아요 반영")
    @PutMapping({"/challenges/{id}", "/quotes/{id}"})
    public ResponseEntity<Void> increaseLikes(@PathVariable Long id){
        quoteService.increaseLikes(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "id로 글귀 불러오기")
    @GetMapping({"/challenges/{id}", "/quotes/{id}"})
    public ResponseEntity<QuoteResponse> getQuoteById(@PathVariable Long id) {
        return ResponseEntity.ok(quoteService.getQuoteById(id));
    }

    @Operation(summary = "id로 글귀 삭제")
    @DeleteMapping("/challenges/{id}")
    public ResponseEntity<Void> deleteQuoteById(@PathVariable Long id) {
        quoteService.deleteQuoteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "작가명으로 글귀 검색")
    @GetMapping({"/challenges/search", "/quotes/search"})
    public ResponseEntity<List<QuoteResponse>> getQuotesByAuthor(@RequestParam("author") String author, Pageable pageable){
        Page<QuoteResponse> quotes = quoteService.getQuotesByAuthor(author, pageable);
        return ResponseEntity.ok(quotes.getContent());
    }
}

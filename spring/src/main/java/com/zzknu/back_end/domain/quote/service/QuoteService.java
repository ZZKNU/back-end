package com.zzknu.back_end.domain.quote.service;

import com.zzknu.back_end.domain.jwt.JwtService;
import com.zzknu.back_end.domain.quote.dto.QuoteRequestDto;
import com.zzknu.back_end.domain.quote.dto.QuoteUpdateRequestDto;
import com.zzknu.back_end.domain.quote.dto.ResponseSuccessful;
import com.zzknu.back_end.domain.quote.entity.Quote;
import com.zzknu.back_end.domain.quote.entity.type.CertifiedType;
import com.zzknu.back_end.domain.quote.repository.QuoteRepository;
import com.zzknu.back_end.domain.user.entity.User;
import com.zzknu.back_end.domain.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuoteService {
    private final QuoteRepository quoteRepository;
    private final JwtService jwtService;
    private final UserService userService;

    public Quote findById(Long quoteId) {
        return quoteRepository.findById(quoteId)
                .orElseThrow(() -> new RuntimeException("Quote not found"));
    }
    // 글귀 등록
    @Transactional
    public ResponseSuccessful createQuote(String accessToken, QuoteRequestDto quoteRequestDto) {
        String email = jwtService.getEmailFromToken(accessToken);
        User existingUser = userService.findByEmail(email);
        if (existingUser == null) {
            throw new RuntimeException("User not found");
        }
        quoteRepository.save(Quote.toEntity(existingUser, quoteRequestDto));
        return ResponseSuccessful.builder().success(true).build();
    }

    // 글귀 수정
    @Transactional
    public ResponseSuccessful updateQuote(String accessToken, QuoteUpdateRequestDto quoteUpdateRequestDto) {
        // accessToken 주인
        String email = jwtService.getEmailFromToken(accessToken);
        User existingUser = userService.findByEmail(email);
        if (existingUser == null) {
            throw new RuntimeException("User not found");
        }
        // 글귀 주인
        Quote quote = quoteRepository.findById(quoteUpdateRequestDto.getId()).orElse(null);
        if (quote == null) {
            throw new RuntimeException("Quote not found");
        }
        // 글귀의 주인인지 판단
        if (existingUser != quote.getUser()) {
            throw new RuntimeException("No permission to update quote");
        }
        else {
            quote.update(quoteUpdateRequestDto);
            return ResponseSuccessful.builder().success(true).build();
        }
    }

    // 모든 글귀 열람 (WAIT)
    public List<Quote> getWaitQuotes(){
        return quoteRepository.findByCertified(false);
    }

    // 모든 글귀 열람 (인증된)
    public List<Quote> getAcceptedQuotes(){
        return quoteRepository.findByCertified(true);
    }

    // id로 글귀 1개 찾기 - 특정 글귀 열람, 글귀 좋아요에도 쓰일 듯
    public Quote getQuoteById(Long id) {
        return quoteRepository.findById(id).orElse(null);
    }

    // 글귀 삭제
    public void deleteQuoteById(Long id) {
        quoteRepository.deleteById(id);
    }

    // 좋아요 반영
    @Transactional
    public void increaseLikes(Long id){
        Quote quote = quoteRepository.findById(id).orElse(null);
        if (quote != null) {
            quote.increaseLikes();
        }
        // quoteRepository.findById(id).ifPresent(Quote::increaseLikes); -> 이렇게 한 줄로 줄여짐;;
    }

    // 작성자로 검색
    public List<Quote> getQuotesByAuthor(String author) {
        return quoteRepository.findByAuthor(author);
    }

    // 제목 검색 기능 and 카테고리별 정렬 기능 미구현
}

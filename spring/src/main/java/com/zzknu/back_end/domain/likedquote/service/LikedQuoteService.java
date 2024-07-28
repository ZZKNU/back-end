package com.zzknu.back_end.domain.likedquote.service;

import com.zzknu.back_end.domain.likedquote.repository.LikedQuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikedQuoteService {
    private final LikedQuoteRepository likedQuoteRepository;
}

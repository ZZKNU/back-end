package com.zzknu.back_end.domain.likedquote.controller;

import com.zzknu.back_end.domain.likedquote.service.LikedQuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikedQuoteController {
    private final LikedQuoteService likedQuoteService;
}

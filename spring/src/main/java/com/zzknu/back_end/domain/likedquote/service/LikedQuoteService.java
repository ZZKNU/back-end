package com.zzknu.back_end.domain.likedquote.service;

import com.zzknu.back_end.domain.likedquote.entity.LikedQuote;
import com.zzknu.back_end.domain.likedquote.repository.LikedQuoteRepository;
import com.zzknu.back_end.domain.quote.entity.Quote;
import com.zzknu.back_end.domain.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikedQuoteService {
    private final LikedQuoteRepository likedQuoteRepository;

    public LikedQuote findByUserAndQuote(User user, Quote quote) {
        return likedQuoteRepository.findByUserAndQuote(user, quote).orElse(null);
    }

    @Transactional
    public boolean getIsLiked(User existingUser, Quote quote) {
        LikedQuote likedQuote = findByUserAndQuote(existingUser, quote);
        if (likedQuote == null) {
            return false;
        }
        System.out.println("좋아요 한 적 있는 게시글");
        return true;
    }

    public void deleteByUserAndQuote(User existingUser, Quote quote) {
        likedQuoteRepository.deleteByUserAndQuote(existingUser, quote);
    }

    public void createByUserAndQuote(User existingUser, Quote quote) {
        LikedQuote likedQuote = new LikedQuote(existingUser, quote);
        likedQuoteRepository.save(likedQuote);
    }
}

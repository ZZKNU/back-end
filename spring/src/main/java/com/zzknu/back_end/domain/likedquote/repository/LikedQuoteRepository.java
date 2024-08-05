package com.zzknu.back_end.domain.likedquote.repository;

import com.zzknu.back_end.domain.likedquote.entity.LikedQuote;
import com.zzknu.back_end.domain.quote.entity.Quote;
import com.zzknu.back_end.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikedQuoteRepository extends JpaRepository<LikedQuote, Long> {

    Optional<LikedQuote> findByUserAndQuote(User user, Quote quote);

    void deleteByUserAndQuote(User existingUser, Quote quote);
}

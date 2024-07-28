package com.zzknu.back_end.domain.likedquote.repository;

import com.zzknu.back_end.domain.likedquote.entity.LikedQuote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikedQuoteRepository extends JpaRepository<LikedQuote, Long> {
}

package com.zzknu.back_end.domain.admin.service;

import com.zzknu.back_end.domain.admin.dto.UserResponse;
import com.zzknu.back_end.domain.jwt.JwtService;
import com.zzknu.back_end.domain.quote.dto.QuoteResponse;
import com.zzknu.back_end.domain.quote.entity.Quote;
import com.zzknu.back_end.domain.quote.service.QuoteService;
import com.zzknu.back_end.domain.user.entity.User;
import com.zzknu.back_end.domain.user.entity.type.AuthorityType;
import com.zzknu.back_end.domain.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserService userService;
    private final JwtService jwtService;
    private final QuoteService quoteService;

    // 유저 권한 변경
    @Transactional
    public boolean updateUserType(String accessToken, Long user_id, AuthorityType authorityType) {
        boolean bool = checkAuthority(accessToken);
        if (bool) {
            return userService.updateUserType(user_id, authorityType);
        }
        return bool;
    }

    // 글귀 베도에서 승격
    @Transactional
    public boolean updateQuote(String accessToken, Long quoteId, Boolean certified) {
        boolean bool = checkAuthority(accessToken);
        if (bool) {
            return quoteService.updateQuoteCertify(quoteId, certified);
        }
        return false;
    }

    // 좋아요 10개 이상인 베스트도전 게시글 모음
    @Transactional
    public Page<QuoteResponse> getQuotes(String accessToken, Pageable pageable) {
        boolean bool = checkAuthority(accessToken);
        if (bool) {
            Page<Quote> quotes = quoteService.getChallengeQuote(pageable);
            return quotes.map(QuoteResponse::new);
        }
        return null;
    }

    //  Admin 권한인지 확인
    public boolean checkAuthority(String accessToken){
        String email =  jwtService.getEmailFromToken(accessToken);
        User user = userService.findByEmail(email);
        return user.getAuthority() == AuthorityType.ADMIN;
    }

    public Page<UserResponse> getUsers(String accessToken, Pageable pageable) {
        boolean bool = checkAuthority(accessToken);
        if (bool) {
            Page<User> users = userService.getAllUser(pageable);
            return users.map(UserResponse::new);
        }
        return null;
    }
}

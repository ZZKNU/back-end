package com.zzknu.back_end.domain.jwt;

import com.zzknu.back_end.config.JwtConfig;
import com.zzknu.back_end.domain.user.entity.User;
import com.zzknu.back_end.domain.user.entity.type.AuthorityType;
import com.zzknu.back_end.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtConfig jwtConfig;

    public String generateToken(String email) {
        return jwtConfig.generateToken(email);
    }

    public boolean validateToken(String token, String email) {
        return jwtConfig.validateToken(token, email);
    }

    public String getEmailFromToken(String token) {
        return jwtConfig.extractEmail(token);
    }

}

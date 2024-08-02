package com.zzknu.back_end.domain.user.service;

import com.zzknu.back_end.config.JwtConfig;
import com.zzknu.back_end.domain.jwt.JwtService;
import com.zzknu.back_end.domain.user.dto.LoginRequestDto;
import com.zzknu.back_end.domain.user.dto.Response;
import com.zzknu.back_end.domain.user.dto.UserRequestDto;
import com.zzknu.back_end.domain.user.entity.User;
import com.zzknu.back_end.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    // 로그인
    public Response login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmailAndPassword(loginRequestDto.getEmail(), loginRequestDto.getPassword()).orElse(null);
        if (user == null) {
            return null;
        }
        String accessToken = jwtService.generateToken(loginRequestDto.getEmail());
        return new Response(accessToken);
    }

    // 회원 가입 (사용자 생성)
    public Response createUser(UserRequestDto userRequestDto) {
        userRepository.save(User.toEntity(userRequestDto));
        String accessToken = jwtService.generateToken(userRequestDto.getEmail());
        return new Response(accessToken);
    }
}

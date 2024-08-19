package com.zzknu.back_end.domain.user.service;

import com.zzknu.back_end.config.JwtConfig;
import com.zzknu.back_end.domain.jwt.JwtService;
import com.zzknu.back_end.domain.user.dto.FindIdDto;
import com.zzknu.back_end.domain.user.dto.LoginRequestDto;
import com.zzknu.back_end.domain.user.dto.Response;
import com.zzknu.back_end.domain.user.dto.UserRequestDto;
import com.zzknu.back_end.domain.user.entity.User;
import com.zzknu.back_end.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
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
    @Transactional
    public Response createUser(UserRequestDto userRequestDto) {
        userRepository.save(User.toEntity(userRequestDto));
        String accessToken = jwtService.generateToken(userRequestDto.getEmail());
        return new Response(accessToken);
    }

    // 이메일 중복 확인
    public Boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    // 닉네임 중복 확인
    public Boolean nicknameExists(String nickname) {
        return userRepository.findByNickname(nickname).isPresent();
    }


    public String findId(FindIdDto findIdDto) {
        User user = userRepository.findByNameAndPhone(findIdDto.getName(), findIdDto.getPhone());
        if (user == null) {
            return "해당하는 회원이 없습니다.";
        }
        return user.getEmail();
    }
}

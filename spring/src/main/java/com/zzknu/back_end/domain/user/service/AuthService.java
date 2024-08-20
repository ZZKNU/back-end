package com.zzknu.back_end.domain.user.service;

import com.zzknu.back_end.domain.jwt.JwtService;
import com.zzknu.back_end.domain.user.dto.*;
import com.zzknu.back_end.domain.user.entity.User;
import com.zzknu.back_end.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
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

    public String findEmail(FindEmailDto findEmailDto) {
        User user = userRepository.findByNameAndPhone(findEmailDto.getName(), findEmailDto.getPhone());
        if (user == null) {
            return "해당하는 회원이 없습니다.";
        }
        return user.getEmail();
    }

    public String chagePassword(ChagePasswordDto chagePasswordDto) {
        User user = userService.findByEmail(chagePasswordDto.getEmail());
        if (user == null) {
            return "아이디를 확인하세요";
        }
        if (!Objects.equals(user.getPassword(), chagePasswordDto.getOldPassword())){
            return "기존 비밀번호를 확인하세요";
        }
        user.setPassword(chagePasswordDto.getNewPassword());
        return "비밀번호 변경에 성공하였습니다.";
    }

    public String createNewPassword(FindPasswordDto findPasswordDto) {
        User user = userRepository.findByNameAndPhone(findPasswordDto.getName(), findPasswordDto.getPhone());
        if (user == null) {
            return "해당하는 회원이 없습니다.";
        }
        if (!Objects.equals(user.getEmail(), findPasswordDto.getEmail())) {
            return "아이디를 확인하세요";
        }
        String tempPassword = userService.createNewPassword(user);
        return "비밀번호가 발급되었습니다.";
    }
}

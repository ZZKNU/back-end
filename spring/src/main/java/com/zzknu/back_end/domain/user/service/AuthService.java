package com.zzknu.back_end.domain.user.service;

import com.zzknu.back_end.domain.user.dto.LoginRequestDto;
import com.zzknu.back_end.domain.user.dto.UserRequestDto;
import com.zzknu.back_end.domain.user.entity.User;
import com.zzknu.back_end.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    // 로그인
    public Long login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmailAndPassword(loginRequestDto.getEmail(), loginRequestDto.getPassword()).orElse(null);
        if (user == null) {
            return null;
        }
        return user.getId();
    }

    // 회원 가입 (사용자 생성)
    public User createUser(UserRequestDto userRequestDto) {
        return userRepository.save(User.toEntity(userRequestDto));
    }
}

package com.zzknu.back_end.domain.user.service;

import com.zzknu.back_end.domain.friendship.dto.FriendInfoDto;
import com.zzknu.back_end.domain.user.dto.UserRequestDto;
import com.zzknu.back_end.domain.user.dto.UserUpdateDto;
import com.zzknu.back_end.domain.user.entity.User;
import com.zzknu.back_end.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 사용자 정보 조회
    public User getUserById(Long userId) {
        return findById(userId);
    }

    // 사용자 정보 수정
    public User updateUser(Long userId, UserUpdateDto updatedUser) {
        User existingUser = findById(userId);
        updatedUser.update(existingUser);

        return userRepository.save(existingUser);
    }

    // 회원 탈퇴
    public void deleteUser(Long userId) {
        User existingUser = findById(userId);
        userRepository.delete(existingUser);
    }

    // 이름으로 사용자 검색
    public Page<FriendInfoDto> findUsersByName(String name, Pageable pageable) {
        Page<User> users =  userRepository.findByNickName(name, pageable);
        if(users.isEmpty()) {
            return Page.empty();
        }
        Page<FriendInfoDto> friendInfoDtos = users.map(FriendInfoDto::new);
        return friendInfoDtos;
    }

    // Id로 사용자 검색
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}

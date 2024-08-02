package com.zzknu.back_end.domain.user.controller;

import com.zzknu.back_end.domain.user.dto.UserInfoDto;
import com.zzknu.back_end.domain.user.dto.UserRequestDto;
import com.zzknu.back_end.domain.user.dto.UserUpdateDto;
import com.zzknu.back_end.domain.user.entity.User;
import com.zzknu.back_end.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 개인 정보 확인 (GET)
    @Operation(summary = "개인 정보 확인")
    @GetMapping
    public ResponseEntity<UserInfoDto> getUserInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {
        UserInfoDto userInfo = userService.getUserInfo(accessToken);
        return ResponseEntity.ok(userInfo);
    }

    // 개인 정보 수정 (PUT)
    @Operation(summary = "개인 정보 수정")
    @PutMapping
    public ResponseEntity<User> updateUserInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken, @RequestBody UserUpdateDto updatedUser) {
        User user = userService.updateUser(accessToken, updatedUser);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping
    public ResponseEntity<Void> deleteUserInfo(@RequestParam Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

}

package com.zzknu.back_end.domain.user.controller;

import com.zzknu.back_end.domain.quote.dto.QuoteResponse;
import com.zzknu.back_end.domain.quote.entity.Quote;
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

import java.util.List;

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
    public ResponseEntity<UserInfoDto> updateUserInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken, @RequestBody UserUpdateDto updatedUser) {
        UserInfoDto user = userService.updateUser(accessToken, updatedUser);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping
    public ResponseEntity<Void> deleteUserInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {
        userService.deleteUser(accessToken);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "좋아요한 글 목록")
    @GetMapping("/liked")
    public ResponseEntity<List<QuoteResponse>> likedQuotes(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {
        return ResponseEntity.ok(userService.getLikedQuotes(accessToken));
    }

}

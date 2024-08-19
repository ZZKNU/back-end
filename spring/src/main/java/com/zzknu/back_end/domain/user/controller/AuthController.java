package com.zzknu.back_end.domain.user.controller;

import com.zzknu.back_end.domain.user.dto.FindIdDto;
import com.zzknu.back_end.domain.user.dto.LoginRequestDto;
import com.zzknu.back_end.domain.user.dto.Response;
import com.zzknu.back_end.domain.user.dto.UserRequestDto;
import com.zzknu.back_end.domain.user.entity.User;
import com.zzknu.back_end.domain.user.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    // 로그인
    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    // 회원 가입
    @Operation(summary = "회원 가입")
    @PostMapping("/join")
    public ResponseEntity<Response> createUser(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(authService.createUser(userRequestDto));
    }

    @Operation(summary = "이메일 중복 확인")
    @GetMapping("/email/{email}")
    public ResponseEntity<Boolean> emailExist(@RequestParam String email) {
        return ResponseEntity.ok(authService.emailExists(email));
    }

    @Operation(summary = "닉네임 중복 확인")
    @GetMapping("/nickname/{nickname}")
    public ResponseEntity<Boolean> nicknameExist(@RequestParam String nickname) {
        return ResponseEntity.ok(authService.nicknameExists(nickname));
    }

    @Operation(summary = "아이디 찾기")
    @PostMapping("/findID")
    public ResponseEntity<String> findID(@RequestBody FindIdDto findIdDto) {
        return ResponseEntity.ok(authService.findId(findIdDto));
    }


}

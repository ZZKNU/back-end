package com.zzknu.back_end.domain.admin.controller;

import com.zzknu.back_end.domain.admin.dto.UserResponse;
import com.zzknu.back_end.domain.admin.service.AdminService;
import com.zzknu.back_end.domain.quote.dto.QuoteResponse;
import com.zzknu.back_end.domain.user.entity.type.AuthorityType;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @Operation(summary = "관리자 권한 업데이트")
    @PutMapping("/authority/{user_id}")
    public boolean updateAdmin(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken, @PathVariable Long user_id, @RequestBody AuthorityType authorityType) {
        return adminService.updateUserType(accessToken, user_id, authorityType);
    }

    @Operation(summary = "글귀 승격")
    @PutMapping("/promotion/{quote_id}")
    public boolean updateQuote(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken, @PathVariable Long quote_id, @RequestBody Boolean certified){
        return adminService.updateQuote(accessToken, quote_id, certified);
    }

    @Operation(summary = "승격 가능한 글귀 목록 가져오기")
    @GetMapping("/challenges")
    public ResponseEntity<List<QuoteResponse>> getQuotes(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken, Pageable pageable) {
        Page<QuoteResponse> quotes = adminService.getQuotes(accessToken, pageable);
        return ResponseEntity.ok(quotes.getContent());
    }

    @Operation(summary = "사용자 리스트 가져오기")
    @GetMapping("/userList")
    public ResponseEntity<List<UserResponse>> getUsers(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken, Pageable pageable) {
        Page<UserResponse> users = adminService.getUsers(accessToken, pageable);
        return ResponseEntity.ok(users.getContent());
    }
}

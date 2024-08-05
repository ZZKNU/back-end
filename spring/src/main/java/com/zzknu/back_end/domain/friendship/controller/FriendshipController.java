package com.zzknu.back_end.domain.friendship.controller;

import com.zzknu.back_end.domain.friendship.dto.FriendInfoDto;
import com.zzknu.back_end.domain.friendship.service.FriendshipService;
import com.zzknu.back_end.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendshipController {
    private final FriendshipService friendshipService;
    private final UserService userService;

    // 친구 검색 - 근데 수정 필요할듯
    @Operation(summary = "친구 검색")
    @GetMapping("/search/{name}")
    public ResponseEntity<List<FriendInfoDto>> searchFriends(@PathVariable String name, Pageable pageable) {
        Page<FriendInfoDto> users = userService.findUsersByName(name, pageable);
        return ResponseEntity.ok(users.getContent());
    }

    // 친구 추가
    @Operation(summary = "친구 추가")
    @PostMapping("/{friend_id}")
    public ResponseEntity<FriendInfoDto> addFriend(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken, @RequestParam Long friend_id) {
        FriendInfoDto friend = friendshipService.addFriend(accessToken, friend_id);
        return ResponseEntity.ok(friend);
    }

    // 친구 목록 - 팔로우
    @Operation(summary = "친구 목록 - 팔로우")
    @GetMapping("/follow")
    public ResponseEntity<List<FriendInfoDto>> getFollowing(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken, Pageable pageable) {
        Page<FriendInfoDto> following = friendshipService.getFollowing(accessToken, pageable);
        return ResponseEntity.ok(following.getContent());
    }

    // 친구 목록 - 팔로워
    @Operation(summary = "친구 목록 - 팔로워")
    @GetMapping("/follower")
    public ResponseEntity<List<FriendInfoDto>> getFollowers(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken, Pageable pageable) {
        Page<FriendInfoDto> followers = friendshipService.getFollowers(accessToken, pageable);
        return ResponseEntity.ok(followers.getContent());
    }

    // 친구 삭제
    @Operation(summary = "친구 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFriend(@PathVariable Long id) {
        friendshipService.deleteFriend(id);
        return ResponseEntity.noContent().build();
    }

}

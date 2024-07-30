package com.zzknu.back_end.domain.friendship.controller;

import com.zzknu.back_end.domain.friendship.dto.FriendInfoDto;
import com.zzknu.back_end.domain.friendship.entity.Friendship;
import com.zzknu.back_end.domain.friendship.service.FriendshipService;
import com.zzknu.back_end.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendshipController {
    private final FriendshipService friendshipService;
    private final UserService userService;

    // 친구 검색
    @GetMapping("/search/{name}")
    public ResponseEntity<Page<FriendInfoDto>> searchFriends(@PathVariable String name, Pageable pageable) {
        Page<FriendInfoDto> users = userService.findUsersByName(name, pageable);
        return ResponseEntity.ok(users);
    }

    // 친구 추가
    @PostMapping("/{user_id}/{friend_id}")
    public ResponseEntity<Friendship> addFriend(@PathVariable Long user_id, @RequestParam Long friend_id) {
        Friendship friendship = friendshipService.addFriend(user_id, friend_id);
        return ResponseEntity.ok(friendship);
    }

    // 친구 목록 - 팔로우
    @GetMapping("/follow/{user_id}")
    public ResponseEntity<Page<FriendInfoDto>> getFollowing(@PathVariable Long user_id, Pageable pageable) {
        Page<FriendInfoDto> following = friendshipService.getFollowing(user_id, pageable);
        return ResponseEntity.ok(following);
    }

    // 친구 목록 - 팔로워
    @GetMapping("/follower/{user_id}")
    public ResponseEntity<Page<FriendInfoDto>> getFollowers(@PathVariable Long user_id, Pageable pageable) {
        Page<FriendInfoDto> followers = friendshipService.getFollowers(user_id, pageable);
        return ResponseEntity.ok(followers);
    }

    // 친구 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFriend(@PathVariable Long id) {
        friendshipService.deleteFriend(id);
        return ResponseEntity.noContent().build();
    }

}

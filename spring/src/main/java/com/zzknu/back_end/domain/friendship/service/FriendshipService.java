package com.zzknu.back_end.domain.friendship.service;

import com.zzknu.back_end.domain.friendship.dto.FriendInfoDto;
import com.zzknu.back_end.domain.friendship.entity.Friendship;
import com.zzknu.back_end.domain.friendship.repository.FriendshipRepository;
import com.zzknu.back_end.domain.user.entity.User;
import com.zzknu.back_end.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class FriendshipService {
    private final FriendshipRepository friendshipRepository;
    private final UserService userService;

    // 친구 추가
    public Friendship addFriend(Long userId, Long friendUserId) {
        User fromUser = userService.findById(userId);
        User toUser = userService.findById(friendUserId);
        Friendship friendship = new Friendship(fromUser, toUser);
        return friendshipRepository.save(friendship);
    }

    // 친구 목록 - 팔로우
    public Page<FriendInfoDto> getFollowing(Long userId, Pageable pageable) {
        Page<User> followingUsers = friendshipRepository.findFollowingByUserId(userId, pageable);
        Page<FriendInfoDto> followings = followingUsers.map(FriendInfoDto::new);
        return followings;
    }

    // 친구 목록 - 팔로워
    public Page<FriendInfoDto> getFollowers(Long userId, Pageable pageable) {
        Page<User> followerUsers = friendshipRepository.findFollowersByUserId(userId, pageable);
        Page<FriendInfoDto> followers = followerUsers.map(FriendInfoDto::new);
        return followers;
    }

    // 친구 삭제
    public void deleteFriend(Long id) {
        friendshipRepository.deleteById(id);
    }
}

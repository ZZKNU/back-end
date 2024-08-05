package com.zzknu.back_end.domain.friendship.service;

import com.zzknu.back_end.domain.friendship.dto.FriendInfoDto;
import com.zzknu.back_end.domain.friendship.entity.Friendship;
import com.zzknu.back_end.domain.friendship.repository.FriendshipRepository;
import com.zzknu.back_end.domain.jwt.JwtService;
import com.zzknu.back_end.domain.user.entity.User;
import com.zzknu.back_end.domain.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class FriendshipService {
    private final FriendshipRepository friendshipRepository;
    private final UserService userService;
    private final JwtService jwtService;

    // 친구 추가
    @Transactional
    public FriendInfoDto addFriend(String accessToken, Long friendUserId) {
        String email = jwtService.getEmailFromToken(accessToken);
        User fromUser = userService.findByEmail(email);
        if (fromUser.getId().equals(friendUserId)) {
            return new FriendInfoDto(new User());
        }
        User toUser = userService.findById(friendUserId);
        if (friendshipRepository.findFriendshipByIdAndId(fromUser.getId(), toUser.getId()).isPresent()) {
            return new FriendInfoDto(new User());
        }
        else{
            Friendship friendship = new Friendship(fromUser, toUser);
            friendshipRepository.save(friendship);
            return new FriendInfoDto(toUser);
        }
    }

    // 친구 목록 - 팔로우
    public Page<FriendInfoDto> getFollowing(String accessToken, Pageable pageable) {
        String email = jwtService.getEmailFromToken(accessToken);
        Page<User> followingUsers = friendshipRepository.findFollowingByEmail(email, pageable);
        if (followingUsers.isEmpty()) {
            return Page.empty();
        }
        Page<FriendInfoDto> followings = followingUsers.map(FriendInfoDto::new);
        return followings;
    }

    // 친구 목록 - 팔로워
    public Page<FriendInfoDto> getFollowers(String accessToken, Pageable pageable) {
        String email = jwtService.getEmailFromToken(accessToken);
        Page<User> followerUsers = friendshipRepository.findFollowersByEmail(email, pageable);
        if (followerUsers.isEmpty()) {
            return Page.empty();
        }
        Page<FriendInfoDto> followers = followerUsers.map(FriendInfoDto::new);
        return followers;
    }

    // 친구 삭제
    public void deleteFriend(Long id) {
        friendshipRepository.deleteById(id);
    }
}

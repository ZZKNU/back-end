package com.zzknu.back_end.domain.friendship.service;

import com.zzknu.back_end.domain.friendship.repository.FriendshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendshipService {
    private final FriendshipRepository friendshipRepository;
}

package com.zzknu.back_end.domain.friendship.controller;

import com.zzknu.back_end.domain.friendship.service.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FriendshipController {
    private final FriendshipService friendshipService;
}

package com.zzknu.back_end.domain.myfortune.controller;

import com.zzknu.back_end.domain.myfortune.service.MyFortuneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyFortuneController {
    private final MyFortuneService myFortuneService;
}

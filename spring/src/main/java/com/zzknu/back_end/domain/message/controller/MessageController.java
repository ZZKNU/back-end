package com.zzknu.back_end.domain.message.controller;

import com.zzknu.back_end.domain.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
}

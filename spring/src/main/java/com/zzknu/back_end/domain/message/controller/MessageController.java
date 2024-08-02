package com.zzknu.back_end.domain.message.controller;

import com.zzknu.back_end.domain.message.dto.MessageListDto;
import com.zzknu.back_end.domain.message.dto.MessageRequest;
import com.zzknu.back_end.domain.message.dto.MessageResponse;
import com.zzknu.back_end.domain.message.entity.Message;
import com.zzknu.back_end.domain.message.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    // 메시지 전송
    @Operation(summary = "메시지 전송")
    @PostMapping("/{to_id}")
    public ResponseEntity<MessageResponse> sendMessage(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken, @PathVariable Long to_id, @RequestBody MessageRequest messageRequest) {
        MessageResponse sentMessage = messageService.sendMessage(accessToken, to_id, messageRequest);
        return ResponseEntity.ok(sentMessage);
    }

    // 받은 메시지 보기 (목록)
    @Operation(summary = "받은 메시지 보기 (목록)")
    @GetMapping("/receive")
    public ResponseEntity<Page<MessageListDto>> getReceivedMessages(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken, Pageable pageable) {
        Page<MessageListDto> receivedMessages = messageService.getReceivedMessages(accessToken, pageable);
        return ResponseEntity.ok(receivedMessages);
    }

    // 보낸 메시지 보기 (목록)
    @Operation(summary = "보낸 메시지 보기 (목록)")
    @GetMapping("/post")
    public ResponseEntity<Page<MessageListDto>> getSentMessages(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken, Pageable pageable) {
        Page<MessageListDto> sentMessages = messageService.getSentMessages(accessToken, pageable);
        return ResponseEntity.ok(sentMessages);
    }

    // 특정 메시지 열람
    @Operation(summary = "특정 메시지 열람")
    @GetMapping("/{message_id}")
    public ResponseEntity<MessageResponse> getMessage(@PathVariable Long message_id) {
        MessageResponse message = messageService.getMessage(message_id);
        return ResponseEntity.ok(message);
    }

    // 메시지 삭제
    @Operation(summary = "메시지 삭제")
    @DeleteMapping("/{message_id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long message_id) {
        messageService.deleteMessage(message_id);
        return ResponseEntity.noContent().build();
    }
}

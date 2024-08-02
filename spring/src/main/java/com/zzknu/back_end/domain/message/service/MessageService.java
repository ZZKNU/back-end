package com.zzknu.back_end.domain.message.service;

import com.zzknu.back_end.domain.jwt.JwtService;
import com.zzknu.back_end.domain.message.dto.MessageListDto;
import com.zzknu.back_end.domain.message.dto.MessageRequest;
import com.zzknu.back_end.domain.message.dto.MessageResponse;
import com.zzknu.back_end.domain.message.entity.Message;
import com.zzknu.back_end.domain.message.repository.MessageRepository;
import com.zzknu.back_end.domain.quote.entity.Quote;
import com.zzknu.back_end.domain.quote.service.QuoteService;
import com.zzknu.back_end.domain.user.entity.User;
import com.zzknu.back_end.domain.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserService userService;
    private final QuoteService quoteService;
    private final JwtService jwtService;
    // 메시지 전송
    public MessageResponse sendMessage(String accessToken, Long toId, MessageRequest messageRequest) {
        String email = jwtService.getEmailFromToken(accessToken);
        User send_user = userService.findByEmail(email);
        User to_user = userService.findById(toId);
        Quote quote = quoteService.findById(messageRequest.getQuote_id());
        Message message = new Message(send_user, to_user, messageRequest.getTitle(),quote);
        messageRepository.save(message);
        return new MessageResponse(message);
    }

    // 받은 메시지 보기
    public Page<MessageListDto> getReceivedMessages(String accessToken, Pageable pageable) {
        String email = jwtService.getEmailFromToken(accessToken);
        User user = userService.findByEmail(email);
        // 받은 메시지 조회
        Page<Message> messages = messageRepository.findByRecvUser(user, pageable);

        // MessageListDto로 변환하여 반환
        return messages.map(MessageListDto::new);
    }

    // 보낸 메시지 보기
    public Page<MessageListDto> getSentMessages(String accessToken, Pageable pageable) {
        String email = jwtService.getEmailFromToken(accessToken);
        User user = userService.findByEmail(email);
        // 보낸 메시지 조회
        Page<Message> messages = messageRepository.findBySendUser(user, pageable);

        // MessageListDto로 변환하여 반환
        return messages.map(MessageListDto::new);
    }

    // 특정 메시지 열람
    @Transactional
    public MessageResponse getMessage(Long messageId) {
        Message message = findById(messageId);
        message.setIsRead();
        MessageResponse messageResponse = new MessageResponse(message);
        return messageResponse;
    }

    // 메시지 삭제
    public void deleteMessage(Long messageId) {
        messageRepository.deleteById(messageId);
    }


    public Message findById(Long messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found"));
    }

}

package com.zzknu.back_end.domain.message.service;

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
    // 메시지 전송
    public Message sendMessage(Long userId, Long toId, MessageRequest messageRequest) {
        User send_user = userService.findById(userId);
        User to_user = userService.findById(toId);
        Quote quote = quoteService.findById(messageRequest.getQuote_id());
        Message message = new Message(send_user, to_user, messageRequest.getTitle(),quote);
        return messageRepository.save(message);
    }

    // 받은 메시지 보기
    public Page<MessageListDto> getReceivedMessages(Long userId, Pageable pageable) {

        // 받은 메시지 조회
        Page<Message> messages = messageRepository.findByRecv_user_Id(userId, pageable);

        // MessageListDto로 변환하여 반환
        return messages.map(MessageListDto::new);
    }

    // 보낸 메시지 보기
    public Page<MessageListDto> getSentMessages(Long userId, Pageable pageable) {

        // 보낸 메시지 조회
        Page<Message> messages = messageRepository.findBySend_user_Id(userId, pageable);

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

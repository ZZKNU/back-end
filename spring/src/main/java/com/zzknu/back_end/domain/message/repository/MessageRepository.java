package com.zzknu.back_end.domain.message.repository;


import com.zzknu.back_end.domain.message.entity.Message;
import com.zzknu.back_end.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

    Page<Message> findByRecvUser(User user, Pageable pageable);

    Page<Message> findBySendUser(User user, Pageable pageable);
}

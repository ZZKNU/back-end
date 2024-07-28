package com.zzknu.back_end.domain.message.repository;


import com.zzknu.back_end.domain.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}

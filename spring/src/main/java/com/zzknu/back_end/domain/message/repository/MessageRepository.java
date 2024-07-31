package com.zzknu.back_end.domain.message.repository;


import com.zzknu.back_end.domain.message.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

    Page<Message> findByRecv_user_Id(Long id, Pageable pageable);

    Page<Message> findBySend_user_Id(Long id, Pageable pageable);
}

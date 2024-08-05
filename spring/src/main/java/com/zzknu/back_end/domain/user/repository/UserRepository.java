package com.zzknu.back_end.domain.user.repository;

import com.zzknu.back_end.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickname);
    Page<User> findByNicknameContaining(String name, Pageable pageable);
}

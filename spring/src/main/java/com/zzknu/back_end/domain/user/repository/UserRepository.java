package com.zzknu.back_end.domain.user.repository;

import com.zzknu.back_end.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

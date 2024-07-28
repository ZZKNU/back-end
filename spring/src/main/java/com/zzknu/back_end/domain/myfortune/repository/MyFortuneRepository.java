package com.zzknu.back_end.domain.myfortune.repository;

import com.zzknu.back_end.domain.myfortune.entity.MyFortune;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyFortuneRepository extends JpaRepository<MyFortune, Long> {
}

package com.zzknu.back_end.domain.myfortune.service;

import com.zzknu.back_end.domain.myfortune.repository.MyFortuneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyFortuneService {
    private final MyFortuneRepository myFortuneRepository;
}

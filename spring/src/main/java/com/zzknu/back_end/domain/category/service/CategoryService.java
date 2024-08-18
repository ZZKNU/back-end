package com.zzknu.back_end.domain.category.service;

import com.zzknu.back_end.domain.category.entity.Category;
import com.zzknu.back_end.domain.category.repository.CategoryRepository;
import com.zzknu.back_end.domain.jwt.JwtService;
import com.zzknu.back_end.domain.quote.dto.ResponseSuccessful;
import com.zzknu.back_end.domain.user.entity.User;
import com.zzknu.back_end.domain.user.entity.type.AuthorityType;
import com.zzknu.back_end.domain.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final JwtService jwtService;
    private final UserService userService;

    // 관리자의 카테고리 생성 - 수정은 삭제 후 생성으로 해결
    @Transactional
    public ResponseSuccessful createCategory(String accessToken, String categoryName) {
        String email = jwtService.getEmailFromToken(accessToken);
        User existingUser = userService.findByEmail(email);
        if (existingUser.getAuthority() != AuthorityType.ADMIN)
            return ResponseSuccessful.builder().success(false).log("Not ADMIN.").build();
        categoryRepository.save(Category.builder().category(categoryName).build());
        return ResponseSuccessful.builder().success(true).build();
    }

    // 관리자의 카테고리 삭제
    public ResponseSuccessful deleteCategory(String accessToken, Long id) {
        String email = jwtService.getEmailFromToken(accessToken);
        User existingUser = userService.findByEmail(email);
        if (existingUser.getAuthority() != AuthorityType.ADMIN)
            return ResponseSuccessful.builder().success(false).log("Not ADMIN.").build();
        categoryRepository.deleteById(id);
        return ResponseSuccessful.builder().success(true).build();
    }

    // 모든 카테고리 열람
    public List<String> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(Category::getCategory)
                .collect(Collectors.toList());
    }
}

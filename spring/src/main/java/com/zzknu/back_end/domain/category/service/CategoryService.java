package com.zzknu.back_end.domain.category.service;

import com.zzknu.back_end.domain.category.dto.ResponseWithLog;
import com.zzknu.back_end.domain.category.entity.Category;
import com.zzknu.back_end.domain.category.repository.CategoryRepository;
import com.zzknu.back_end.domain.jwt.JwtService;
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
    public ResponseWithLog createCategory(String accessToken, String categoryName) {
        String email = jwtService.getEmailFromToken(accessToken);
        User existingUser = userService.findByEmail(email);
        if (existingUser.getAuthority() != AuthorityType.ADMIN)
            return ResponseWithLog.builder().success(false).log("Not ADMIN.").build();
        categoryRepository.save(Category.builder().categoryName(categoryName).build());
        return ResponseWithLog.builder().success(true).build();
    }

    // 관리자의 카테고리 삭제
    public ResponseWithLog deleteCategory(String accessToken, Long id) {
        String email = jwtService.getEmailFromToken(accessToken);
        User existingUser = userService.findByEmail(email);
        if (existingUser.getAuthority() != AuthorityType.ADMIN)
            return ResponseWithLog.builder().success(false).log("Not ADMIN.").build();
        categoryRepository.deleteById(id);
        return ResponseWithLog.builder().success(true).build();
    }

    // 모든 카테고리 열람
    public List<String> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(Category::getCategoryName)
                .collect(Collectors.toList());
    }

    // 카테고리 이름으로 특정 카테고리 반환
    public Category findCategoryByName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName).orElseThrow(() -> new RuntimeException("Category not found."));
    }
}

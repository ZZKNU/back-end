package com.zzknu.back_end.domain.category.repository;

import com.zzknu.back_end.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    public Optional<Category> findByCategoryName(String categoryName);
}

package com.itsjbges.blog.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itsjbges.blog.domain.entities.Category;
import com.itsjbges.blog.repositories.CategoryRepository;
import com.itsjbges.blog.services.CategoryService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAllWithPostCount();
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {

        String categoryName = category.getName();

        if (categoryRepository.existsByNameIgnoreCase(categoryName)) {
            throw new IllegalArgumentException("Category already exists with name " + categoryName);
        }

        return categoryRepository.save(category);
    }

}

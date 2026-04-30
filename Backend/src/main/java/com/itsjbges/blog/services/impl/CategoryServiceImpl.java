package com.itsjbges.blog.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itsjbges.blog.domain.entities.Category;
import com.itsjbges.blog.repositories.CategoryRepository;
import com.itsjbges.blog.services.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAllWithPostCount();
    }
    
}

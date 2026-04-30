package com.itsjbges.blog.services;

import java.util.List;

import com.itsjbges.blog.domain.entities.Category;

public interface CategoryService {
    List<Category> listCategories();
    Category createCategory(Category category);
}

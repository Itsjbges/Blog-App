package com.itsjbges.blog.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itsjbges.blog.domain.dtos.CategoryDto;
import com.itsjbges.blog.domain.entities.Category;
import com.itsjbges.blog.mappers.CategoryMapper;
import com.itsjbges.blog.services.CategoryService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories() {
        List<CategoryDto> categories = categoryService.listCategories()
                    .stream().map(categoryMapper::toDto)
                    .toList();
        
        return ResponseEntity.ok(categories);
    }
    
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(){
        //TODO: process POST request
        
        return null;
    }
    
}

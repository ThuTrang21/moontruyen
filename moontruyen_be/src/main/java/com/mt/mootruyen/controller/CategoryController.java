package com.mt.mootruyen.controller;


import com.mt.mootruyen.dto.request.ApiResponse;
import com.mt.mootruyen.dto.request.CategoryCreationRequest;
import com.mt.mootruyen.dto.request.CategoryUdatingRequest;
import com.mt.mootruyen.entity.Category;
import com.mt.mootruyen.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    ApiResponse<List<Category>> getAllCategories() {
        return ApiResponse.<List<Category>>builder()
                .data(categoryService.getAllCategories())
                .build();
    }

    @GetMapping("/id/{categoryId}")
    ApiResponse<Category> getCategoryById(@PathVariable String categoryId) {
        return ApiResponse.<Category>builder()
                .data(categoryService.getCategoryById(categoryId))
                .build();
    }

    @GetMapping("{slug}")
    ApiResponse<Category> getCategoryBySlug(@PathVariable String slug) {
        return ApiResponse.<Category>builder()
                .data(categoryService.getCategoryBySlug(slug))
                .build();
    }

    @PostMapping
    ApiResponse<Category> createCategory(@RequestBody CategoryCreationRequest request) {
        return ApiResponse.<Category>builder()
                .data(categoryService.createCategory(request))
                .build();
    }

    @PutMapping("{categoryId}")
    ApiResponse<Category> updateCategory(@RequestBody CategoryUdatingRequest request, @PathVariable("categoryId") String categoryId) {
        return ApiResponse.<Category>builder()
                .data(categoryService.updateCategory(request, categoryId))
                .build();
    }

    @DeleteMapping("{categoryId}")
    ApiResponse<String> deleteCategory(@PathVariable("categoryId") String categoryId) {
        categoryService.deleteCategory(categoryId);
        return ApiResponse.<String>builder()
                .message("Category deleted successfully")
                .build();
    }
}

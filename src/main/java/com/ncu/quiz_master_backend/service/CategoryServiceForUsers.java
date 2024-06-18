package com.ncu.quiz_master_backend.service;

import com.ncu.quiz_master_backend.entity.Category;

import java.util.List;

public interface CategoryServiceForUsers {
    Category getCategoryInfoByCategoryId(Integer categoryId);

    List<Category> getAllCategories();
}

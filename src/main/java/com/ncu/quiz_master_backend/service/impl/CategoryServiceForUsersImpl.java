package com.ncu.quiz_master_backend.service.impl;

import com.ncu.quiz_master_backend.entity.Category;
import com.ncu.quiz_master_backend.mapper.CategoryMapperForUsers;
import com.ncu.quiz_master_backend.service.CategoryServiceForUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceForUsersImpl implements CategoryServiceForUsers {
    @Autowired
    private CategoryMapperForUsers categoryMapperForUsers;

    @Override
    public Category getCategoryInfoByCategoryId(Integer categoryId){
        return categoryMapperForUsers.getCategoryInfoByCategoryId(categoryId);
    };

    @Override
    public List<Category> getAllCategories(){
        return categoryMapperForUsers.getAllCategories();
    }
}

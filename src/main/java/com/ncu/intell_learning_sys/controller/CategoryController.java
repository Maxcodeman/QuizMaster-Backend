package com.ncu.intell_learning_sys.controller;


import com.ncu.intell_learning_sys.entity.Category;
import com.ncu.intell_learning_sys.entity.Result;
import com.ncu.intell_learning_sys.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author max
 * @since 2023-12-04
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping
    public Result getAllCategories(){
        List<Category> categoryList=new ArrayList<>();
        categoryList=iCategoryService.getAllCategories();
        log.info("获取全部题目分类信息:{}",categoryList);
        return Result.success(categoryList);
    }
}

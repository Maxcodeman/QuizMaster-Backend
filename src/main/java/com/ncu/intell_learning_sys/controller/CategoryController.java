package com.ncu.intell_learning_sys.controller;


import com.ncu.intell_learning_sys.entity.Category;
import com.ncu.intell_learning_sys.entity.Result;
import com.ncu.intell_learning_sys.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Result addCategory(@RequestParam String categoryName){
        int column=iCategoryService.addCategory(categoryName);
        if(column==1){
            log.info("添加分类成功");
            return Result.success();
        }else{
            log.info("添加分类失败");
            return Result.error("添加分类失败");
        }
    }
}

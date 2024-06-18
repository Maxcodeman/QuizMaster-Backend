package com.ncu.quiz_master_backend.controller.admin;


import com.ncu.quiz_master_backend.entity.Result;
import com.ncu.quiz_master_backend.service.CategoryServiceForUsers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
public class CategotyControllerForUsers {

    @Autowired
    private CategoryServiceForUsers categoryServiceForUsers;

    @GetMapping("/users/categories")
    public Result getCategoryInfoByCategoryId(Integer categoryId){
        log.info("根据分类ID={}获取分类信息", categoryId);
        return Result.success(categoryServiceForUsers.getCategoryInfoByCategoryId(categoryId));
    }

    @PostMapping("/users/categories")
    public Result getAllCategories(){
        log.info("获取全部分类信息");
        return Result.success(categoryServiceForUsers.getAllCategories());
    }
}

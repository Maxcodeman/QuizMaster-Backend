package com.ncu.quiz_master_backend.controller.admin;


import com.ncu.quiz_master_backend.entity.Category;
import com.ncu.quiz_master_backend.entity.Result;
import com.ncu.quiz_master_backend.service.ICategoryService;
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

    @DeleteMapping("/{ids}")
    public Result deleteByIds(@PathVariable List<Long> ids){
        int columns=iCategoryService.deleteByIds(ids);
        log.info("删除的分类数"+columns);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        Category category=iCategoryService.selectById(id);
        if(category==null){
            return Result.error("获取分类失败");
        }else{
            log.info("根据id查询到的题目:{}",category);
            return Result.success(category);
        }
    }

    @PutMapping
    public Result editById(@RequestParam Integer id,@RequestParam(required = false) String name){
        int column=iCategoryService.editById(id,name);
        log.info("修改的分类数:"+column);
        if(column==1){
            return Result.success();
        }else{
            return Result.error("修改失败");
        }
    }
}

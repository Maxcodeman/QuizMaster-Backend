package com.ncu.quiz_master_backend.controller.admin;


import com.ncu.quiz_master_backend.entity.Category;
import com.ncu.quiz_master_backend.entity.PageBean;
import com.ncu.quiz_master_backend.entity.Question;
import com.ncu.quiz_master_backend.entity.Result;
import com.ncu.quiz_master_backend.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/categories")
public class CategoryController {
    @Autowired
    private ICategoryService iCategoryService;
    @GetMapping
    public Result listAll(@RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          String categoryName){
        PageBean pageBean = iCategoryService.listAll(page,pageSize,categoryName);
        return Result.success(pageBean);
    }

    @DeleteMapping("/{id}")
    public Result removeById(@PathVariable Integer id){
        iCategoryService.removeById(id);
        return Result.success();
    }

    @PostMapping
    public Result addOne(@RequestParam String categoryName){
        log.info("addOne接收到的:"+categoryName);
        iCategoryService.addOne(categoryName);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Category category=iCategoryService.getById(id);
        return Result.success(category);
    }
    @PutMapping
    public Result modify(@RequestBody Category category){
        iCategoryService.modify(category);
        return Result.success();
    }
}

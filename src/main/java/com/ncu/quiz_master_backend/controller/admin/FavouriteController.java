package com.ncu.quiz_master_backend.controller.admin;

import com.ncu.quiz_master_backend.entity.PageBean;
import com.ncu.quiz_master_backend.entity.Result;
import com.ncu.quiz_master_backend.service.FavouriteService;
import com.ncu.quiz_master_backend.service.IQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/favorite")
public class FavouriteController {
    @Autowired
    private FavouriteService favouriteService;
    @GetMapping
    public Result listAll(@RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          Integer userId, Integer questionId){
        PageBean pageBean = favouriteService.listAll(page,pageSize,userId,questionId);
        return Result.success(pageBean);
    }
    @DeleteMapping("/{id}")
    public Result removeById(@PathVariable Integer id){
        favouriteService.removeById(id);
        return Result.success();
    }

}

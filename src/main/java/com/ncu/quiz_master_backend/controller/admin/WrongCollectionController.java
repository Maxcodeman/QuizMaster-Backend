package com.ncu.quiz_master_backend.controller.admin;

import com.ncu.quiz_master_backend.anno.Log;
import com.ncu.quiz_master_backend.entity.PageBean;
import com.ncu.quiz_master_backend.entity.Result;
import com.ncu.quiz_master_backend.service.WrongCollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin/wrong")
public class WrongCollectionController {
    @Autowired
    private WrongCollectionService wrongCollectionService;
    @GetMapping
    public Result listAll(@RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          Integer userId, Integer questionId){
        PageBean pageBean = wrongCollectionService.listAll(page,pageSize,userId,questionId);
        return Result.success(pageBean);
    }

    @Log
    @DeleteMapping("/{id}")
    public Result removeById(@PathVariable Integer id){
        wrongCollectionService.removeById(id);
        return Result.success();
    }
}

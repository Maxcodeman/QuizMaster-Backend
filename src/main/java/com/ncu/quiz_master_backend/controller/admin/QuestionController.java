package com.ncu.quiz_master_backend.controller.admin;


import com.ncu.quiz_master_backend.anno.Log;
import com.ncu.quiz_master_backend.entity.PageBean;
import com.ncu.quiz_master_backend.entity.Question;
import com.ncu.quiz_master_backend.entity.Result;
import com.ncu.quiz_master_backend.service.IQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/admin/questions")
public class QuestionController {
    @Autowired
    private IQuestionService iQuestionService;
    @GetMapping
    public Result listAll(@RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          String questionDesc, Integer categoryId, Integer type){
        PageBean pageBean = iQuestionService.listAll(page,pageSize,questionDesc,categoryId,type);
        return Result.success(pageBean);
    }

    @Log
    @DeleteMapping("/{ids}")
    public Result removeById(@PathVariable List<Integer> ids){
        iQuestionService.removeById(ids);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result addOne(@RequestBody Question question){
        iQuestionService.addOne(question);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Question question=iQuestionService.getById(id);
        return Result.success(question);
    }

    @Log
    @PutMapping
    public Result modify(@RequestBody Question question){
        iQuestionService.modify(question);
        return Result.success();
    }

    @Log
    @PostMapping("/upload")
    public Result upload(MultipartFile file, Integer categoryId) throws IOException {
        iQuestionService.upload(file,categoryId);
        return Result.success();
    }
}

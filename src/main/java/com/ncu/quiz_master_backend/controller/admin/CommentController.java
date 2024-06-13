package com.ncu.quiz_master_backend.controller.admin;


import com.ncu.quiz_master_backend.anno.Log;
import com.ncu.quiz_master_backend.entity.Comment;
import com.ncu.quiz_master_backend.entity.PageBean;
import com.ncu.quiz_master_backend.entity.Result;
import com.ncu.quiz_master_backend.service.ICommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/admin/comments")
public class CommentController {
    @Autowired
    private ICommentService iCommentService;

    @GetMapping
    public Result selectCommentByConditions(Integer commentId,
                                            Integer userId,
                                            Integer questionId,
                                            LocalDateTime startTime,
                                            LocalDateTime endTime,
                                            @RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("commentId:{},userID:{},QuestionID:{}, startTime:{},endTime:{},page:{},pageSize:{}",
                commentId, userId, questionId, startTime, endTime, page, pageSize);
        PageBean pageBean = iCommentService.selectCommentByConditions(commentId, userId, questionId, startTime, endTime, page, pageSize);
        return Result.success(pageBean);
    }

    @Log
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){
        log.info("delete id={}",id);
        iCommentService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        log.info("select by id = {}", id);
        Comment comment = iCommentService.selectById(id);
        return Result.success(comment);
    }

}

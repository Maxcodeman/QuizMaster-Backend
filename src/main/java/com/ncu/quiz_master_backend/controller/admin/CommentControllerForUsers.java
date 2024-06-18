package com.ncu.quiz_master_backend.controller.admin;


import com.ncu.quiz_master_backend.entity.CommentForUsers;
import com.ncu.quiz_master_backend.entity.CommentInfo;
import com.ncu.quiz_master_backend.entity.Result;
import com.ncu.quiz_master_backend.service.ICommentServiceForUsers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users/comments")
public class CommentControllerForUsers {
    @Autowired
    private ICommentServiceForUsers iCommentServiceForUsers;

    @PostMapping
    public Result insertComment(@RequestBody CommentForUsers commentForUsers){
        log.info("user {} post a comment to question {}, comment is {}",
                commentForUsers.getUserId(), commentForUsers.getQuestionId(), commentForUsers.getCommentContent());
        iCommentServiceForUsers.insertComment(commentForUsers.getUserId(), commentForUsers.getQuestionId(), commentForUsers.getCommentContent());
        return Result.success();
    }

    @GetMapping("/{questionId}")
    public Result selectByQuestionId(@PathVariable Integer questionId){
        log.info("select comments by id = {}",questionId);
        List<CommentInfo> commentForUsersList=iCommentServiceForUsers.selectByQuestionId(questionId);
        log.info(commentForUsersList.toString());
        return Result.success(commentForUsersList);
    }



}

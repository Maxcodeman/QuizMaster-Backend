package com.ncu.quiz_master_backend.service;

import com.ncu.quiz_master_backend.entity.Comment;
import com.ncu.quiz_master_backend.entity.PageBean;

import java.time.LocalDateTime;

public interface ICommentService {
    PageBean selectCommentByConditions(Integer commentId,
                                       Integer userId,
                                       Integer questionId,
                                       LocalDateTime startTime,
                                       LocalDateTime endTime,
                                       Integer page,
                                       Integer pageSize);

    void deleteById(Integer id);

    Comment selectById(Integer id);

}

package com.ncu.quiz_master_backend.service;

import com.ncu.quiz_master_backend.entity.PageBean;

public interface ICommentServiceForUsers {

    void insertComment(Integer userId, Integer questionId, String commentContent);

    PageBean selectByQuestionId(Integer questionId, Integer page, Integer pageSize);
}

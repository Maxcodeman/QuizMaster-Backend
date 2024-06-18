package com.ncu.quiz_master_backend.service;

import com.ncu.quiz_master_backend.entity.CommentInfo;
import com.ncu.quiz_master_backend.entity.PageBean;

import java.util.List;

public interface ICommentServiceForUsers {

    void insertComment(Integer userId, Integer questionId, String commentContent);

    List<CommentInfo> selectByQuestionId(Integer questionId);
}

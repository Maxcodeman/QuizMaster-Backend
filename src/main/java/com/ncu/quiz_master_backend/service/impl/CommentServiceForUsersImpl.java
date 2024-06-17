package com.ncu.quiz_master_backend.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ncu.quiz_master_backend.entity.CommentForUsers;
import com.ncu.quiz_master_backend.entity.PageBean;
import com.ncu.quiz_master_backend.mapper.CommentMapperForUser;
import com.ncu.quiz_master_backend.service.ICommentServiceForUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentServiceForUsersImpl implements ICommentServiceForUsers {

    @Autowired
    private CommentMapperForUser commentMapperForUser;


    @Override
    public void insertComment(Integer userId, Integer questionId, String commentContent){
        commentMapperForUser.insertComment(userId, questionId, commentContent);
    }

    @Override
    public PageBean selectByQuestionId(Integer questionId, Integer page, Integer pageSize){
        PageHelper.startPage(page, pageSize);
        List<CommentForUsers> commentForUsersList = commentMapperForUser.selectByQuestionId(questionId);
        Page<CommentForUsers> p = (Page<CommentForUsers>) commentForUsersList;
        return new PageBean(p.getTotal(), p.getResult());
    }


}

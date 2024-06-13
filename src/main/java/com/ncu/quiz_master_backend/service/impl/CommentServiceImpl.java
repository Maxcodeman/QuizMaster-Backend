package com.ncu.quiz_master_backend.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ncu.quiz_master_backend.entity.Comment;
import com.ncu.quiz_master_backend.entity.PageBean;
import com.ncu.quiz_master_backend.mapper.CommentMapper;
import com.ncu.quiz_master_backend.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public PageBean selectCommentByConditions(Integer commentId,
                                              Integer userId,
                                              Integer questionId,
                                              LocalDateTime startTime,
                                              LocalDateTime endTime,
                                              Integer page,
                                              Integer pageSize){
        PageHelper.startPage(page,pageSize);
        List<Comment> commentList = commentMapper.selectCommentByConditions(commentId, userId, questionId,startTime, endTime);
        Page<Comment> p = (Page<Comment>) commentList;
        return new PageBean(p.getTotal(),p.getResult());
    }


    @Override
    public void deleteById(Integer id){
        commentMapper.deleteById(id);
    };

    @Override
    public Comment selectById(Integer id){
        Comment comment = commentMapper.selectById(id);
        return comment;
    };

}

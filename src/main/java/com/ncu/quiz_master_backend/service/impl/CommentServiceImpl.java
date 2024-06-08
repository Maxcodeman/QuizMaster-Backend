package com.ncu.quiz_master_backend.service.impl;

import com.ncu.quiz_master_backend.entity.Comment;
import com.ncu.quiz_master_backend.mapper.CommentMapper;
import com.ncu.quiz_master_backend.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author max
 * @since 2023-12-04
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}

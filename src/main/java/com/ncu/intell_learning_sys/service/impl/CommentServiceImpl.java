package com.ncu.intell_learning_sys.service.impl;

import com.ncu.intell_learning_sys.entity.Comment;
import com.ncu.intell_learning_sys.mapper.CommentMapper;
import com.ncu.intell_learning_sys.service.ICommentService;
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

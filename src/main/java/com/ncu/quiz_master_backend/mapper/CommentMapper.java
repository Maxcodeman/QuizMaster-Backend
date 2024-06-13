package com.ncu.quiz_master_backend.mapper;

import com.ncu.quiz_master_backend.entity.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface CommentMapper {

    List<Comment> selectCommentByConditions(Integer commentId,
                                            Integer userId,
                                            Integer questionId,
                                            LocalDateTime startTime,
                                            LocalDateTime endTime
                                            );

    @Delete("delete from tb_comment where comment_id = #{id}")
    void deleteById(Integer Id);

    @Select("select * from tb_comment where comment_id = #{commentId}")
    Comment selectById(Integer id);
}

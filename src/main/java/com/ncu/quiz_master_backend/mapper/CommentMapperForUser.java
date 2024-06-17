package com.ncu.quiz_master_backend.mapper;

import com.ncu.quiz_master_backend.entity.CommentForUsers;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface CommentMapperForUser {

    @Insert("insert into tb_comment(user_id, question_id, comment_content, create_time) VALUE " +
            "(#{userId}, #{questionId}, #{commentContent}, now())")
    void insertComment(Integer userId, Integer questionId, String commentContent);

    @Select("select * from tb_comment where question_id = #{questionId}")
    List<CommentForUsers> selectByQuestionId(Integer questionId);
}

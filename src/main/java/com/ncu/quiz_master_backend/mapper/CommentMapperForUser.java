package com.ncu.quiz_master_backend.mapper;

import com.ncu.quiz_master_backend.entity.CommentForUsers;
import com.ncu.quiz_master_backend.entity.CommentInfo;
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

    @Select("select tc.comment_id, tu.user_id, tu.username,tu.avatar, tc.comment_content, tc.create_time " +
            "from tb_comment as tc join tb_user tu on tc.user_id = tu.user_id where tc.question_id = #{questionId}")
    List<CommentInfo> selectByQuestionId(Integer questionId);
}

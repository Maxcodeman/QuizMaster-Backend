package com.ncu.quiz_master_backend.mapper;


import com.ncu.quiz_master_backend.entity.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface NoteMapperForUsers {

    @Select("select note_content from tb_note where user_id = #{userId} and question_id = #{questionId}")
    String selectById(Integer userId, Integer questionId);

    @Update("update tb_note set note_content = #{noteContent} where user_id = #{userId} and question_id = #{questionId}")
    void updateById(Integer userId, Integer questionId, String noteContent);

    @Insert("insert into tb_note(user_id, question_id, note_content) VALUE (#{userId},#{questionId},#{noteContent})")
    void insertNote(Integer userId, Integer questionId, String noteContent);

    @Select("select count(1) from tb_note where user_id = #{userId} and question_id = #{questionId}")
    Integer checkById(Integer userId, Integer questionId);

}

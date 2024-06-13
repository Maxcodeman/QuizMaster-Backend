package com.ncu.quiz_master_backend.mapper;

import com.ncu.quiz_master_backend.entity.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    List<Note> selectNoteByConditions(Integer noteId, Integer userId);

    @Delete("delete from tb_note where note_id =#{id}")
    void deleteById(Integer id);

    @Insert("insert into tb_note(user_id, question_id, note_content) VALUE (#{userId},#{questionId},#{noteContent})")
    void insertNote(Integer userId, Integer questionId, String noteContent);

    @Select("select * from tb_note where note_id = #{id}")
    Note selectById(Integer id);

    @Update("update tb_note set " +
            "note_content=#{noteContent}, user_id=#{userId}, question_id=#{questionId} " +
            "where note_id = #{noteId}")
    void updateNote(Integer noteId, Integer userId, Integer questionId, String noteContent);
}

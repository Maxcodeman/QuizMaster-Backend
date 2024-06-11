package com.ncu.quiz_master_backend.service;

import com.ncu.quiz_master_backend.entity.Note;
import com.ncu.quiz_master_backend.entity.PageBean;

public interface INoteService {

    PageBean selectNoteByConditions(Integer userId, Integer noteId, Integer page, Integer pageSize);

    void deleteById(Integer id);

    void insertNote(Integer userId, Integer questionId, String noteContent);

    Note selectById(Integer id);

    void updateNote(Integer noteId, Integer userId, Integer questionId, String noteContent);
}

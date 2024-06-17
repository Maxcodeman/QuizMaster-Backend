package com.ncu.quiz_master_backend.service;

import com.ncu.quiz_master_backend.entity.Note;

import java.util.List;

public interface INoteServiceForUsers {
    Note selectById(Integer userId, Integer questionId);

    void updateById(Integer userId, Integer questionId, String noteContent);

    void insertNote(Integer userId, Integer questionId, String noteContent);

    List<Note> checkById(Integer userId, Integer questionId);
}

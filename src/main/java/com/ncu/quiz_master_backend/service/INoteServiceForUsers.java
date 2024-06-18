package com.ncu.quiz_master_backend.service;

import com.ncu.quiz_master_backend.entity.Note;

import java.util.List;

public interface INoteServiceForUsers {
    String selectById(Integer userId, Integer questionId);

    void updateById(Integer userId, Integer questionId, String noteContent);

    void insertNote(Integer userId, Integer questionId, String noteContent);

    Integer checkById(Integer userId, Integer questionId);
}

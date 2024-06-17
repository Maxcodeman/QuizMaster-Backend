package com.ncu.quiz_master_backend.service.impl;

import com.ncu.quiz_master_backend.entity.Note;
import com.ncu.quiz_master_backend.mapper.NoteMapperForUsers;
import com.ncu.quiz_master_backend.service.INoteServiceForUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceForUserImpl implements INoteServiceForUsers {
    @Autowired
    private NoteMapperForUsers noteMapperForUsers;

    @Override
    public Note selectById(Integer userId, Integer questionId){
        return noteMapperForUsers.selectById(userId, questionId);
    }

    @Override
    public void updateById(Integer userId, Integer questionId, String noteContent){
        noteMapperForUsers.updateById(userId, questionId, noteContent);
    }

    @Override
    public void insertNote(Integer userId, Integer questionId, String noteContent){
        noteMapperForUsers.insertNote(userId, questionId, noteContent);
    }

    @Override
    public List<Note> checkById(Integer userId, Integer questionId){
        return noteMapperForUsers.checkById(userId, questionId);
    }

}

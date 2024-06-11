package com.ncu.quiz_master_backend.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ncu.quiz_master_backend.entity.Note;
import com.ncu.quiz_master_backend.entity.PageBean;
import com.ncu.quiz_master_backend.mapper.NoteMapper;
import com.ncu.quiz_master_backend.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements INoteService {

    @Autowired
    private NoteMapper noteMapper;


    @Override
    public PageBean selectNoteByConditions(Integer userId, Integer noteId, Integer page, Integer pageSize){
        PageHelper.startPage(page,pageSize);
        List<Note> noteList = noteMapper.selectNoteByConditions(noteId, userId);
        Page<Note> p =(Page<Note>) noteList;
        return new PageBean(p.getTotal(),p.getResult());
    }

    @Override
    public void deleteById(Integer id){
        noteMapper.deleteById(id);
    }

    @Override
    public void insertNote(Integer userId, Integer questionId, String noteContent){
        noteMapper.insertNote(userId, questionId, noteContent);
    }

    @Override
    public Note selectById(Integer id){
        Note note = noteMapper.selectById(id);
        return note;
    };

    @Override
    public void updateNote(Integer noteId, Integer userId, Integer questionId, String noteContent){
        noteMapper.updateNote(noteId, userId, questionId, noteContent);
    }
}

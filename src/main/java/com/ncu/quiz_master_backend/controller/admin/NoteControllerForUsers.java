package com.ncu.quiz_master_backend.controller.admin;


import com.ncu.quiz_master_backend.entity.Note;
import com.ncu.quiz_master_backend.entity.Result;
import com.ncu.quiz_master_backend.service.INoteServiceForUsers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users/notes")
public class NoteControllerForUsers {
    @Autowired
    private INoteServiceForUsers iNoteServiceForUsers;

    @GetMapping
    public Result selectById(@RequestParam("userId") Integer userId,
                             @RequestParam("questionId") Integer questionId){
        log.info("select id = {}, question id = {}", userId, questionId);
        Note noteList = iNoteServiceForUsers.selectById(userId, questionId);
        return Result.success(noteList);
    }

    @PutMapping
    public Result updateById(@RequestBody Note note){
        log.info("update userid = {}, questionId={}, noteContent = {}",note.getUserId(),note.getQuestionId(),note.getNoteContent());
        iNoteServiceForUsers.updateById(note.getUserId(),note.getQuestionId(),note.getNoteContent());
        return Result.success();
    }

    @PostMapping
    public Result insertNote(@RequestBody Note note){
        log.info("insert userid = {}, questionId={}, noteContent = {}",note.getUserId(),note.getQuestionId(),note.getNoteContent());
        iNoteServiceForUsers.insertNote(note.getUserId(),note.getQuestionId(),note.getNoteContent());
        return Result.success();
    }

    @GetMapping("/check")
    public Result checkById(@RequestParam("userId") Integer userId,
                            @RequestParam("questionId") Integer questionId){
        log.info("check id = {}, question id = {}", userId, questionId);
        List<Note> note = iNoteServiceForUsers.checkById(userId, questionId);
        if (note!=null) {return Result.success(1);}
        else{ return Result.success(0);}
    }

}

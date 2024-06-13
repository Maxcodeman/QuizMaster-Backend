package com.ncu.quiz_master_backend.controller.admin;

import com.ncu.quiz_master_backend.anno.Log;
import com.ncu.quiz_master_backend.entity.Note;
import com.ncu.quiz_master_backend.entity.PageBean;
import com.ncu.quiz_master_backend.entity.Result;
import com.ncu.quiz_master_backend.service.INoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/admin/notes")
public class NoteController {
    @Autowired
    private INoteService iNoteService;


    @GetMapping
    public Result selectNoteByConditions(Integer userId,
                                         Integer noteId,
                                         @RequestParam(defaultValue = "1") Integer page,
                                         @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("getNote:userId:{},noteId:{},page:{},pageSize:{}",userId, noteId, page, pageSize);
        PageBean pageBean = iNoteService.selectNoteByConditions(userId, noteId, page, pageSize);
        return Result.success(pageBean);
    }

    @Log
    @DeleteMapping("/{noteId}")
    public Result deleteById(@PathVariable Integer noteId){
        log.info("delete:{}",noteId);
        iNoteService.deleteById(noteId);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result insertNote(@RequestBody Note note){
        log.info("newNote:{},with userId set {}, questionId = {}",note.getNoteContent(),note.getUserId(),note.getQuestionId());
        iNoteService.insertNote(note.getUserId(),note.getQuestionId(), note.getNoteContent());
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        log.info("select note id={}",id);
        Note note = iNoteService.selectById(id);
        return Result.success(note);
    }

    @Log
    @PutMapping
    public Result updateNote(@RequestBody Note note){
        log.info("update noteid={}, userId={}, questionId={}, note content={}",
                note.getNoteId(),note.getUserId(),note.getQuestionId(),note.getNoteContent());
        iNoteService.updateNote(note.getNoteId(), note.getUserId(), note.getQuestionId(), note.getNoteContent());
        return Result.success();
    }
}

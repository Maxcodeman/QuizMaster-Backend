package com.ncu.quiz_master_backend.controller.admin;


import com.ncu.quiz_master_backend.anno.Log;
import com.ncu.quiz_master_backend.entity.Message;
import com.ncu.quiz_master_backend.entity.PageBean;
import com.ncu.quiz_master_backend.entity.Result;
import com.ncu.quiz_master_backend.service.IMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/admin/messages")
public class MessageController {
    @Autowired
    private IMessageService iMessageService;

    @GetMapping
    public Result selectMessageByConditions(Integer adminId,
                                            LocalDateTime startTime,
                                            LocalDateTime endTime,
                                            @RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("adminId: {}, startTime: {}, endTime: {}, page: {}, pageSize: {}", adminId, startTime, endTime, page, pageSize);
        PageBean pageBean = iMessageService.selectMessageByConditions(adminId, startTime, endTime, page, pageSize);
        return Result.success(pageBean);
    }

    @Log
    @DeleteMapping("/{MessageId}")
    public Result deleteById(@PathVariable Integer MessageId){
        log.info("delete:{}", MessageId);
        iMessageService.deleteById(MessageId);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result insertMessage(@RequestBody Message message){
        log.info("newlog:{}, by {}",message.getAdminId(), message.getMessageContent());
        iMessageService.insertMessage(message.getAdminId(), message.getMessageContent());
        return Result.success();
    }

    @GetMapping("/{messageId}")
    public Result selectById(@PathVariable Integer messageId){
        log.info("select message id={}",messageId);
        Message message = iMessageService.selectById(messageId);
        return Result.success(message);
    }

    @Log
    @PutMapping
    public Result updateMessage(@RequestBody Message message){
        log.info("change message: id= {}, messagecontent={},view={}",
                message.getMessageId(),
                message.getMessageContent(),
                message.getView());
        iMessageService.updateMessage(message.getMessageId(),
                                      message.getMessageContent(),
                                      message.getView()
                                      );
        return Result.success();
    };

}

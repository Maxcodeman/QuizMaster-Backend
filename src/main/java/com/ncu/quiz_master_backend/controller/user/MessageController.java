package com.ncu.quiz_master_backend.controller.user;

import com.ncu.quiz_master_backend.entity.Message;
import com.ncu.quiz_master_backend.entity.Result;
import com.ncu.quiz_master_backend.service.IMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class MessageController {

    @Autowired
    private IMessageService iMessageService;

    @GetMapping("/messages")
    public Result getAllMessages(){
        List<Message> messageList = iMessageService.getAllMessages();
        return Result.success(messageList);
    }
}

package com.ncu.quiz_master_backend.service;

import com.ncu.quiz_master_backend.entity.Message;
import com.ncu.quiz_master_backend.entity.PageBean;
import java.time.LocalDateTime;
import java.util.List;

public interface IMessageService {
    PageBean selectMessageByConditions(Integer adminId, LocalDateTime startTime, LocalDateTime endTime, Integer page, Integer pageSize);
    void deleteById(Integer id);

    void insertMessage(Integer adminId, String messageContent);

    Message selectById(Integer messageID);

    void updateMessage(Integer messageId, String messageContent, Integer view);


    List<Message> getAllMessages();
}

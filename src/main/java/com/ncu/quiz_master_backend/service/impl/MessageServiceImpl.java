package com.ncu.quiz_master_backend.service.impl;


import com.github.pagehelper.PageHelper;
import com.ncu.quiz_master_backend.entity.Message;
import com.ncu.quiz_master_backend.entity.PageBean;
import com.ncu.quiz_master_backend.mapper.MessageMapper;
import com.ncu.quiz_master_backend.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements IMessageService {
    @Autowired
    private MessageMapper messageMapper;


    @Override
    public PageBean selectMessageByConditions(Integer adminId,
                                             LocalDateTime startTime,
                                             LocalDateTime endTime,
                                             Integer page,
                                             Integer pageSize){
        PageHelper.startPage(page,pageSize);
        List<Message> messageList = messageMapper.selectMessageByConditions(adminId,startTime,endTime);
        Page<Message> p = (Page<Message>) messageList;
        return new PageBean(p.getTotal(), p.getResult());
    }

    @Override
    public void deleteById(Integer id){
        messageMapper.deleteById(id);
    }

    @Override
    public void insertMessage(Integer adminId, String messageContent){
        messageMapper.insertMessage(adminId, messageContent);
    }

    @Override
    public Message selectById(Integer messageId){
        Message message = messageMapper.selectById(messageId);
        return message;
    }

    @Override
    public void updateMessage(Integer messageId, String messageContent, Integer view){
        messageMapper.updateMessage(messageId, messageContent, view);
    }
}

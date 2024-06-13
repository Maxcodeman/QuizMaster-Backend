package com.ncu.quiz_master_backend.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ncu.quiz_master_backend.entity.Admin;
import com.ncu.quiz_master_backend.entity.Log;
import com.ncu.quiz_master_backend.entity.PageBean;
import com.ncu.quiz_master_backend.mapper.LogMapper;
import com.ncu.quiz_master_backend.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;
    @Override
    public PageBean list(Integer adminId, LocalDateTime startTime, LocalDateTime endTime, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Log> logList = logMapper.list(adminId, startTime, endTime);
        Page<Log> p= (Page<Log>)logList;

        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }
}

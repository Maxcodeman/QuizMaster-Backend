package com.ncu.quiz_master_backend.service;

import com.ncu.quiz_master_backend.entity.PageBean;

import java.time.LocalDateTime;

public interface LogService {
    PageBean list(Integer adminId, LocalDateTime startTime,LocalDateTime endTime,Integer page,Integer pageSize);
}

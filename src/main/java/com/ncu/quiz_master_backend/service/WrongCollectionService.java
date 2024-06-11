package com.ncu.quiz_master_backend.service;

import com.ncu.quiz_master_backend.entity.PageBean;

public interface WrongCollectionService {
    PageBean listAll(Integer page, Integer pageSize, Integer userId, Integer questionId);

    void removeById(Integer id);
}

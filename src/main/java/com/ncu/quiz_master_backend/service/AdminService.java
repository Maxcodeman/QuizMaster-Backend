package com.ncu.quiz_master_backend.service;

import com.ncu.quiz_master_backend.entity.Admin;
import com.ncu.quiz_master_backend.entity.PageBean;


public interface AdminService {
    Admin queryById(Integer id);

    void deleteById(Integer id);

    void update(Admin admin);

    PageBean list(String name, Integer page, Integer pageSize);

    void insert(Admin admin);
}

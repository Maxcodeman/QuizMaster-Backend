package com.ncu.quiz_master_backend.service;

import com.ncu.quiz_master_backend.entity.Admin;
import com.ncu.quiz_master_backend.entity.PageBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author max
 * @since 2023-12-04
 */
public interface AdminService {
    Admin queryById(Integer id);

    void deleteById(Integer id);

    void update(Admin admin);

    PageBean list(String name, Integer page, Integer pageSize);

    void insert(Admin admin);
}

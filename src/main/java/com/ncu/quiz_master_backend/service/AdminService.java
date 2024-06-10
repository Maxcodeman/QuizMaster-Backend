package com.ncu.quiz_master_backend.service;

import com.ncu.quiz_master_backend.entity.Admin;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author max
 * @since 2023-12-04
 */
public interface AdminService {
    public Admin query(Integer id);

    void deleteById(Integer id);

    void update(Admin admin);
}

package com.ncu.quiz_master_backend.service;

import com.ncu.quiz_master_backend.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author max
 * @since 2023-12-04
 */
public interface IAdminService extends IService<Admin> {
    int changePassword(Long id,String oldPassword,String password);

    Admin getInfoById(String id);

    int updateInfoById(Long id, String name, String telephone);
}

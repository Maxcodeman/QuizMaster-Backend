package com.ncu.intell_learning_sys.service;

import com.ncu.intell_learning_sys.entity.Admin;
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

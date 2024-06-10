package com.ncu.quiz_master_backend.service.impl;

import com.ncu.quiz_master_backend.entity.Admin;
import com.ncu.quiz_master_backend.mapper.AdminMapper;
import com.ncu.quiz_master_backend.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 管理员服务实现类
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin query(Integer id) {
        return adminMapper.queryById(id);
    }

    @Override
    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }

    @Override
    public void update(Admin admin) {
        adminMapper.update(admin);
    }
}

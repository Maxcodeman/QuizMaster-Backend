package com.ncu.quiz_master_backend.service.impl;

import com.ncu.quiz_master_backend.entity.Admin;
import com.ncu.quiz_master_backend.entity.PageBean;
import com.ncu.quiz_master_backend.mapper.AdminMapper;
import com.ncu.quiz_master_backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员服务实现类
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin queryById(Integer id) {
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

    @Override
    public PageBean list(String name, Integer page, Integer pageSize) {
        List<Admin> adminList = adminMapper.list(name, (page - 1) * pageSize, pageSize);
        Long count=0L;
        if(adminList!=null){
            count= Long.valueOf(adminList.size());
        }
        PageBean pageBean = new PageBean(count, adminList);
        return pageBean;
    }

    @Override
    public void insert(Admin admin) {
        adminMapper.insert(admin);
    }
}

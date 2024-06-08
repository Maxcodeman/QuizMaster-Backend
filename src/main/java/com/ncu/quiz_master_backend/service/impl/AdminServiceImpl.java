package com.ncu.quiz_master_backend.service.impl;

import com.ncu.quiz_master_backend.entity.Admin;
import com.ncu.quiz_master_backend.mapper.AdminMapper;
import com.ncu.quiz_master_backend.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author max
 * @since 2023-12-04
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public int changePassword(Long id, String oldPassword, String password) {
        Admin a = adminMapper.selectById(id);
        //没有该账户
        if(a==null) {
            return 0;
        }else{
            //密码输入错误
            if(!a.getPassword().equals(oldPassword)){
                return 2;
            }else{
                a.setPassword(password);
                return adminMapper.updateById(a);
            }
        }
    }

    @Override
    public Admin getInfoById(String id) {
        return adminMapper.selectById(id);
    }

    @Override
    public int updateInfoById(Long id, String name, String telephone) {
        Admin a = adminMapper.selectById(id);

        //没有该账户
        if(a==null){
            return 0;

        }else{
            //重新设置名字和密码
            a.setAdminName(name);
            a.setTelephone(telephone);

            adminMapper.updateById(a);
            return 1;
        }
    }
}

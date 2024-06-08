package com.ncu.quiz_master_backend.service.impl;

import com.ncu.quiz_master_backend.entity.User;
import com.ncu.quiz_master_backend.mapper.UserMapper;
import com.ncu.quiz_master_backend.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}

package com.ncu.intell_learning_sys.service.impl;

import com.ncu.intell_learning_sys.entity.User;
import com.ncu.intell_learning_sys.mapper.UserMapper;
import com.ncu.intell_learning_sys.service.IUserService;
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

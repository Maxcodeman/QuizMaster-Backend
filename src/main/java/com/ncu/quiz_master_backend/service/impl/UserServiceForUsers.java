package com.ncu.quiz_master_backend.service.impl;

import com.ncu.quiz_master_backend.entity.User;
import com.ncu.quiz_master_backend.entity.UserInfo;
import com.ncu.quiz_master_backend.mapper.UserMapperForUsers;
import com.ncu.quiz_master_backend.service.IUserServiceForUsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceForUsers implements IUserServiceForUsers {
    @Autowired
    private UserMapperForUsers userMapperForUsers;

    @Override
    public void updatePassword(Integer userId, String oldPassword, String newPassword){
        userMapperForUsers.updatePassword(userId, oldPassword, newPassword);
    }

    @Override
    public UserInfo selectById(Integer userId){
        return userMapperForUsers.selectById(userId);
    }

    @Override
    public void updateInfo(Integer userId, String username, String avatar, String mobile){
        userMapperForUsers.updateInfo(userId,username,avatar,mobile);
    }

    @Override
    public User queryById(String mobile){
        return userMapperForUsers.queryById(mobile);
    }

    @Override
    public String queryPasswordById(Integer userId){ return userMapperForUsers.queryPasswordById(userId); }

    @Override
    public void register(User user) {
        user.setUpdateTime(LocalDateTime.now());
        user.setCreateTime(LocalDateTime.now());
        user.setState(true);
        user.setAvatar("1.jpg");
       userMapperForUsers.insertUser(user);
    }

    ;
}

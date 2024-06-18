package com.ncu.quiz_master_backend.service;

import com.ncu.quiz_master_backend.entity.User;
import com.ncu.quiz_master_backend.entity.UserInfo;

public interface IUserServiceForUsers {
    void updatePassword(Integer userId, String oldPassword, String newPassword);

    UserInfo selectById(Integer userId);

    void updateInfo(Integer userId, String username, String avatar, String mobile);

    User queryById(String mobile);

    String queryPasswordById(Integer userId);
}

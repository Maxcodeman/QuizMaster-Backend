package com.ncu.quiz_master_backend.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ncu.quiz_master_backend.entity.PageBean;
import com.ncu.quiz_master_backend.entity.User;
import com.ncu.quiz_master_backend.mapper.UserMapper;
import com.ncu.quiz_master_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void removeById(List<Integer> ids) {
        userMapper.deleteById(ids);
    }

    @Override
    public PageBean listAll(Integer page, Integer pageSize, String  username, String mobile) {
        PageHelper.startPage(page,pageSize);
        List<User> userList = userMapper.selectAll(username,mobile);
        Page<User> p = (Page<User>)userList;
        return new PageBean(p.getTotal(),p.getResult());
    }

    @Override
    public void addOne(User user) {
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setState(true);
        userMapper.insert(user);
    }

    @Override
    public User getById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public void modify(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void changeState(User user) {
        userMapper.updateState(user);
    }
}

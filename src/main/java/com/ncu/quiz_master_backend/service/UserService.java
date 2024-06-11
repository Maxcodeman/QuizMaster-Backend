package com.ncu.quiz_master_backend.service;

import com.ncu.quiz_master_backend.entity.PageBean;
import com.ncu.quiz_master_backend.entity.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserService {
    void removeById(List<Integer> ids);


    PageBean listAll(Integer page, Integer pageSize, String  username, String mobile);

    void addOne(User user);

    User getById(Integer id);

    void modify(User user);

    void changeState(User user);
}

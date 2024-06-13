package com.ncu.quiz_master_backend.controller.admin;


import com.ncu.quiz_master_backend.anno.Log;
import com.ncu.quiz_master_backend.entity.PageBean;
import com.ncu.quiz_master_backend.entity.Question;
import com.ncu.quiz_master_backend.entity.Result;
import com.ncu.quiz_master_backend.entity.User;
import com.ncu.quiz_master_backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/users")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping
    public Result listAll(@RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          String  username, String mobile){
        PageBean pageBean = userService.listAll(page,pageSize,username,mobile);
        return Result.success(pageBean);
    }

    @Log
    @DeleteMapping("/{ids}")
    public Result removeById(@PathVariable List<Integer> ids){
        userService.removeById(ids);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result addOne(@RequestBody User user){
        userService.addOne(user);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        User user=userService.getById(id);
        return Result.success(user);
    }

    @Log
    @PutMapping
    public Result modify(@RequestBody User user){
        userService.modify(user);
        return Result.success();
    }

    @Log
    @PutMapping("/activate")
    public Result changeState(@RequestBody User user){
        userService.changeState(user);
        return Result.success();
    }
}

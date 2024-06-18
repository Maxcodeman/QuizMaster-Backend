package com.ncu.quiz_master_backend.controller.admin;


import com.ncu.quiz_master_backend.entity.Result;
import com.ncu.quiz_master_backend.entity.User;
import com.ncu.quiz_master_backend.entity.UserInfo;
import com.ncu.quiz_master_backend.service.IUserServiceForUsers;
import com.ncu.quiz_master_backend.service.impl.UserServiceForUsers;
import com.ncu.quiz_master_backend.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping
public class UserControllerForUsers {
    @Autowired
    private IUserServiceForUsers iUserServiceForUsers;

    @PutMapping("/users/password")
    public Result updatePassword(@RequestParam("userId") Integer userId,
                                 @RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword){
        log.info("password update user = {}, new password = {}",userId, newPassword);
        String olds = iUserServiceForUsers.queryPasswordById(userId);
        if(!Objects.equals(olds, oldPassword)){
            return Result.error("错误：旧密码输入错误");
        } else if (Objects.equals(oldPassword, newPassword)) {
            return Result.error("错误：旧密码和新密码相同！");
        } else{
            iUserServiceForUsers.updatePassword(userId, oldPassword, newPassword);
            return Result.success();
        }

    }

    @GetMapping("/users/info/{userId}")
    public Result selectById(@PathVariable Integer userId){
        UserInfo user = iUserServiceForUsers.selectById(userId);
        return Result.success(user);
    }

    @PutMapping("/users/info")
    public Result updateInfo(@RequestBody User user){
        log.info("user {} update information, name = {}, avatar = {}, mobile = {}",
                user.getUserId(),
                user.getUsername(),
                user.getAvatar(),
                user.getMobile());
        iUserServiceForUsers.updateInfo(user.getUserId(), user.getUsername(), user.getAvatar(), user.getMobile());
        return Result.success();
    }

    @PostMapping("/users/login")
    public Result userLogin(@RequestBody User user, @RequestParam(required=false) Boolean remember){
        String password = user.getPassword();
        String mobile = user.getMobile();
        User a = iUserServiceForUsers.queryById(mobile);
        if(a==null){
            return Result.error("登陆失败，不存在该用户");
        } else if (!a.getMobile().equals(mobile)) {
            return Result.error("手机号不正确！");
        } else if(!a.getPassword().equals(password)){
            return Result.error("密码不正确！");
        }else{
            log.info("用户登录：{}",mobile);
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",a.getUserId());
            claims.put("admin_name",a.getUsername());
            String jwt;
            if(remember != null && remember){
                jwt = JwtUtils.generateJwtWithExpire(claims);
            }else{
                jwt = JwtUtils.generateJwtWithoutExpire(claims);
            }
            return Result.success(jwt);
        }
    }
}

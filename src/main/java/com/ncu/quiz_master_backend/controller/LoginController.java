package com.ncu.quiz_master_backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ncu.quiz_master_backend.entity.Admin;
import com.ncu.quiz_master_backend.entity.Result;
import com.ncu.quiz_master_backend.service.IAdminService;
import com.ncu.quiz_master_backend.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private IAdminService iAdminService;

    @PostMapping("/login")
    public Result login(@RequestBody Admin admin,@RequestParam(required = false) Boolean remember){
        String password=admin.getPassword();

        //向数据库中查询是否有对应的管理员
        LambdaQueryWrapper<Admin> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Admin::getAdminId,admin.getAdminId());
        Admin a=iAdminService.getOne(lambdaQueryWrapper);

        //没有查询到该管理员
        if(a==null){
            return Result.error("登录失败,不存在该账户");
        }

        //密码不正确
        if(!a.getPassword().equals(password)){
            return Result.error("登录失败,密码不正确");
        }

        //账号被禁用
        if(a.getAdminState()==0){
            return Result.error("账号已禁用");
        }

        log.info("管理员登录: {}",a);
        Map<String,Object> claims=new HashMap<>();
        claims.put("id",a.getAdminId());

        String jwt=null;
        //是否保持登录
        if(remember){
            jwt= JwtUtils.generateJwtWithExpire(claims);
        }
        else {
            jwt = JwtUtils.generateJwtWithoutExpire(claims);
        }
        return Result.success(jwt);
    }
}

package com.ncu.quiz_master_backend.controller;


import com.ncu.quiz_master_backend.entity.Admin;
import com.ncu.quiz_master_backend.entity.Result;
import com.ncu.quiz_master_backend.service.IAdminService;
import com.ncu.quiz_master_backend.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author max
 * @since 2023-12-04
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IAdminService iAdminService;

    /*从token中获取个人ID*/
    @GetMapping("/id")
    public Result getId(HttpServletRequest request){
        /*获取请求头中的令牌*/
        String token=request.getHeader("token");

        /*解析出令牌中的id信息*/
        Claims claims = JwtUtils.parseJWT(token);
        String id=claims.get("id").toString();

        return Result.success(id);
    }

    /*修改密码*/
    @PutMapping("/password")
    public Result changePassword(Long id,String oldPassword,String password){
        /*修改成功*/
        int res=iAdminService.changePassword(id,oldPassword,password);
        if(res==2){
            return Result.error("修改密码失败,旧密码输入错误");
        }else if(res==0){
            return Result.error("修改密码失败,不存在该账户");
        }else{
            return Result.success();
        }
    }

    /*根据token查询管理员信息*/
    @GetMapping("/info")
    public Result getInfo(HttpServletRequest request){
        /*获取请求头中的令牌*/
        String token=request.getHeader("token");

        /*解析出令牌中的id信息*/
        Claims claims = JwtUtils.parseJWT(token);
        String id=claims.get("id").toString();

        /*根据id查询个人信息*/
        Admin a = iAdminService.getInfoById(id);
        if(a==null){
            log.info("获取信息失败");
            return Result.error("获取信息失败");
        }else{
            log.info("获取信息成功:{}",a);
            return Result.success(a);
        }
    }

    /*根据id修改管理员信息*/
    @PutMapping("/info")
    public Result updateInfoById(Long id,String name,String telephone){
        int res=iAdminService.updateInfoById(id,name,telephone);
        if(res==1) return Result.success();
        else return Result.error("修改个人信息失败");
    }

}

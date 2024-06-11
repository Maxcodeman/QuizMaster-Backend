package com.ncu.quiz_master_backend.controller.admin;

import com.ncu.quiz_master_backend.entity.Admin;
import com.ncu.quiz_master_backend.entity.PageBean;
import com.ncu.quiz_master_backend.entity.Result;
import com.ncu.quiz_master_backend.service.AdminService;
import com.ncu.quiz_master_backend.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 管理员登录（可以选择是否保持登录状态）
     * @param admin
     * @param remember
     * @return
     */
    @PostMapping("/login")
    public Result adminLogin(@RequestBody Admin admin,@RequestParam(required=false) Boolean remember){
        //传递的密码
        String password=admin.getPassword();
        //获取传递的管理员id
        int id=admin.getAdminId();
        //查询是否有对应的管理员
        Admin a = adminService.queryById(id);
        //数据库中没有该管理员信息
        if(a==null){
            return Result.error("登录失败，不存在该账户");
            //密码不正确
        }else if(!a.getPassword().equals(password)){
            return Result.error("登录失败，密码不正确");
        }else{
            log.info("管理员登录: {}",a);
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",a.getAdminId());
            claims.put("admin_name",a.getAdminName());
            //jwt令牌
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

    @PutMapping("/password")
    public Result changePassword(@RequestParam Integer id,@RequestParam String oldPassword,@RequestParam String newPassword){
        //根据id查找用户
        Admin a = adminService.queryById(id);
        //比对密码是否相同
        if(!a.getPassword().equals(oldPassword)){
            return Result.error("密码不正确");
        }else if(oldPassword.equals(newPassword)){
            return Result.error("新旧密码相同");
        }else{
            a.setPassword(newPassword);
            adminService.update(a);
            return Result.success();
        }
    }

    /**
     * 条件分页查询
     * @param name
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/admins")
    public Result query(@RequestParam(required = false) String name,@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "10") Integer pageSize){
        PageBean pageBean = adminService.list(name, page, pageSize);
        return Result.success(pageBean);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/admins/{id}")
    public Result queryById(@PathVariable Integer id){
        Admin admin = adminService.queryById(id);
        return Result.success(admin);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/admins/{id}")
    public Result deleteById(@PathVariable Integer id){
        adminService.deleteById(id);
        log.info("删除管理员: {}",id);
        return Result.success();
    }

    /**
     * 更新
     * @param admin
     * @return
     */
    @PutMapping("/admins")
    public Result update(@RequestBody Admin admin){
        log.info("更新管理员: {}",admin);
        adminService.update(admin);
        return Result.success();
    }

    /**
     * 修改
     * @param admin
     * @return
     */
    @PostMapping("/admins")
    public Result insert(@RequestBody Admin admin){
        adminService.insert(admin);
        log.info("新增管理员: {}",admin);
        return Result.success();
    }

}

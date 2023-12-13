package com.ncu.intell_learning_sys.controller;


import com.ncu.intell_learning_sys.entity.Result;
import com.ncu.intell_learning_sys.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
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
@RestController
@RequestMapping("/admin")
public class AdminController {
    /*从token中获取个人ID*/
    @GetMapping("/getid")
    public Result getId(HttpServletRequest request){
        /*获取请求头中的令牌*/
        String token=request.getHeader("token");

        /*解析出令牌中的id信息*/
        Claims claims = JwtUtils.parseJWT(token);
        String id=claims.get("id").toString();

        return Result.success(id);
    }
}

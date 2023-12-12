package com.ncu.intell_learning_sys.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.ncu.intell_learning_sys.entity.Result;
import com.ncu.intell_learning_sys.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*1.获取请求url*/
        String url=request.getRequestURL().toString();
        log.info("请求的url: {}",url);

        /*2.判断请求url中是否包含login,如果包含则证明是登录操作,放行*/
        if(url.contains("login")){
            log.info("登录操作,放行...");
            return true;
        }

        /*3.获取请求头中的令牌*/
        String jwt=request.getHeader("token");

        /*4.判断令牌是否存在,如果不存在则直接返回错误信息*/
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空,返回未登录的信息");
            Result error= Result.error("未登录");

            //手动转换 对象-->json
            String notLogin= JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }

        /*5.解析token*/
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析token失败,返回未登录的信息");
            Result error = Result.error("登录状态超过有效期");

            String notLogin= JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }

        log.info("令牌合法,放行");
        return true;
    }
}

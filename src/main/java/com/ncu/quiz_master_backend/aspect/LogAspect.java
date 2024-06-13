package com.ncu.quiz_master_backend.aspect;

import com.alibaba.fastjson.JSONObject;
import com.ncu.quiz_master_backend.entity.Log;
import com.ncu.quiz_master_backend.mapper.LogMapper;
import com.ncu.quiz_master_backend.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {
    @Autowired
    private LogMapper logMapper;

    @Autowired
    private HttpServletRequest request;

    @Around("@annotation(com.ncu.quiz_master_backend.anno.Log)")
    public Object LogOperate(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //管理员ID
        String jwt=request.getHeader("token");
        Claims claims= JwtUtils.parseJWT(jwt);
        Integer adminId=(Integer) claims.get("id");

        LocalDateTime operateTime=LocalDateTime.now();
        log.info("操作时间: {}",operateTime);

        //操作类名
        String className=proceedingJoinPoint.getTarget().getClass().getName();
        log.info("操作类名: {}",className);

        //操作方法名
        String methodName=proceedingJoinPoint.getSignature().getName();
        log.info("操作方法名: {}",methodName);

        //操作方法参数
        Object[] args = proceedingJoinPoint.getArgs();
        String methodParams= Arrays.toString(args);
        log.info("操作方法参数: {}",methodParams);

        Object object =proceedingJoinPoint.proceed();//调用原始方法

        //操作方法返回值
        String returnValue= JSONObject.toJSONString(object);
        log.info("操作方法返回值: {}",returnValue);


        Log operateLog=new Log(null,adminId,operateTime,className,methodName,methodParams,returnValue);
        logMapper.insert(operateLog);
        log.info("AOP记录操作日志: {}",log);
        return object;
    }
}

package com.ncu.quiz_master_backend.controller.admin;

import com.ncu.quiz_master_backend.entity.PageBean;
import com.ncu.quiz_master_backend.entity.Result;
import com.ncu.quiz_master_backend.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/admin/logs")
public class LogController {
    @Autowired
    private LogService logService;
    @GetMapping
    public Result list(@RequestParam(required = false) Integer adminId, @RequestParam(required = false) LocalDateTime startTime,
                       @RequestParam(required = false) LocalDateTime endTime,@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        PageBean pageBean = logService.list(adminId, startTime, endTime, page, pageSize);
        return Result.success(pageBean);
    }
}

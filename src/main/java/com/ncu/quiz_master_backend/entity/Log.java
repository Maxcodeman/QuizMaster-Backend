package com.ncu.quiz_master_backend.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 日志实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log{
    private Integer logId;

    private Integer adminId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private String className;//操作类名

    private String methodName;//操作方法名

    private String methodParams;//操作方法参数

    private String returnValue;//操作方法返回值
}

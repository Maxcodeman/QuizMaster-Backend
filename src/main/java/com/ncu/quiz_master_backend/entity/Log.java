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

    private String logContent;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}

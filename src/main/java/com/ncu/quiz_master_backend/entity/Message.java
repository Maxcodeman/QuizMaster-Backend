package com.ncu.quiz_master_backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 资讯实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private Integer messageId;
    private Integer adminId;
    private String messageContent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    private Integer view; //浏览量
}

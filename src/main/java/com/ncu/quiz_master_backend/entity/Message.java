package com.ncu.quiz_master_backend.entity;

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

    private LocalDateTime createTime;
    //浏览量
    private Integer view;
}

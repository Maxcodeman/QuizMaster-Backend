package com.ncu.quiz_master_backend.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 评论实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment{
    private Integer commentId;
    private Integer userId;
    private Integer questionId;
    private String commentContent;
    private LocalDateTime createTime;
}

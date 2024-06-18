package com.ncu.quiz_master_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentInfo {
    private Integer commentId;
    private Integer userId;
    private String username;
    private String avatar;
    private String commentContent;
    private LocalDateTime createTime;
}

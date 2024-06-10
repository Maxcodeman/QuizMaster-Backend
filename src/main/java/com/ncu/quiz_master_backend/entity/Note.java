package com.ncu.quiz_master_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 笔记实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note{
    private Integer noteId;

    private Integer userId;

    private Integer questionId;

    private String noteContent;
}

package com.ncu.quiz_master_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 错误题目实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WrongQuestion{
    private Integer wrongId;
    private Integer userId;
    private Integer questionId;
    //错误次数
    private Integer wrongCount=1;
}

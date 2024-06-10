package com.ncu.quiz_master_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Category {
    private Integer categoryId;

    private String categoryName;
    //分类题目的数量，默认为0
    private Integer questionCount=0;
}

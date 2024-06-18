package com.ncu.quiz_master_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionForUsers {
    private Integer questionId;
    private String questionDesc;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;
    private Integer categoryId;
    private Integer wrongCount;
    private Boolean isFavorite=false;
    //题型（单选、多选或判断）
    private Integer type;

}

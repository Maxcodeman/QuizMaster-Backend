package com.ncu.quiz_master_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 收藏题目实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteQuestion {
    private Integer favoriteId;

    private Integer questionId;

    private Integer userId;

    //是否收藏，0未收藏，1已收藏,默认为已收藏
    private Boolean isFavorite=true;
}

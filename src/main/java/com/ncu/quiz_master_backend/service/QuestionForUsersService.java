package com.ncu.quiz_master_backend.service;

import com.ncu.quiz_master_backend.entity.FavoriteQuestion;
import com.ncu.quiz_master_backend.entity.Question;
import com.ncu.quiz_master_backend.entity.QuestionForUsers;
import com.ncu.quiz_master_backend.entity.WrongQuestion;

import java.util.List;

public interface QuestionForUsersService {
    List<QuestionForUsers> getAllByCategoryId(Integer categoryId,Integer userId);

    //检查错题记录是否存在
    Integer checkWrongExist(Integer questionId, Integer userId);
    //更改错题次数
    void modifyWrongCount(WrongQuestion wrongQuestion);
    //添加错题记录
    void addWrong(WrongQuestion wrongQuestion);


    //检查收藏记录是否存在
    Integer checkFavoriteExist(Integer questionId, Integer userId);
    //更改收藏状态
    void modifyFavoriteState(FavoriteQuestion favoriteQuestion);
    //添加收藏记录
    void addFavorite(FavoriteQuestion favoriteQuestion);
}

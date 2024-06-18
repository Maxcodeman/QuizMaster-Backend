package com.ncu.quiz_master_backend.service.impl;

import com.ncu.quiz_master_backend.entity.FavoriteQuestion;
import com.ncu.quiz_master_backend.entity.Question;
import com.ncu.quiz_master_backend.entity.QuestionForUsers;
import com.ncu.quiz_master_backend.entity.WrongQuestion;
import com.ncu.quiz_master_backend.mapper.QuestionForUsersMapper;
import com.ncu.quiz_master_backend.service.QuestionForUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionForUsersServiceImpl implements QuestionForUsersService {

    @Autowired
    private QuestionForUsersMapper questionForUsersMapper;
    @Override
    public List<QuestionForUsers> getAllByCategoryId(Integer categoryId,Integer userId) {

        return questionForUsersMapper.selectAllByCategoryId(categoryId,userId);
    }

    @Override
    public Integer checkFavoriteExist(Integer questionId, Integer userId) {
        return questionForUsersMapper.checkFavoriteExist(questionId,userId);
    }
    @Override
    public void modifyFavoriteState(FavoriteQuestion favoriteQuestion) {
        questionForUsersMapper.updateFavoriteState(favoriteQuestion);
    }

    @Override
    public void addFavorite(FavoriteQuestion favoriteQuestion) {
        questionForUsersMapper.insertFavorite(favoriteQuestion);
    }
    @Override
    public Integer checkWrongExist(Integer questionId, Integer userId) {
        return questionForUsersMapper.checkWrongExist(questionId,userId);
    }
    @Override
    public void modifyWrongCount(WrongQuestion wrongQuestion) {
        questionForUsersMapper.updateWrongCount(wrongQuestion);
    }

    @Override
    public void addWrong(WrongQuestion wrongQuestion) {
        questionForUsersMapper.insertWrong(wrongQuestion);
    }

    @Override
    public List<QuestionForUsers> getFavourByUserId(Integer userId){
        return questionForUsersMapper.getFavourByUserId(userId);
    }

    @Override
    public List<QuestionForUsers> getWrongByUserId(Integer userId){
        return questionForUsersMapper.getWrongByUserId(userId);
    };


}

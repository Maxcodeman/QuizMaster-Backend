package com.ncu.quiz_master_backend.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ncu.quiz_master_backend.entity.FavoriteQuestion;
import com.ncu.quiz_master_backend.entity.PageBean;
import com.ncu.quiz_master_backend.mapper.FavouriteMapper;
import com.ncu.quiz_master_backend.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteServiceImpl implements FavouriteService {
    @Autowired
    private FavouriteMapper favouriteMapper;
    @Override
    public PageBean listAll(Integer page, Integer pageSize, Integer userId, Integer questionId) {
        PageHelper.startPage(page,pageSize);
        List<FavoriteQuestion> favoriteQuestionList = favouriteMapper.selectAll(userId,questionId);
        Page<FavoriteQuestion> p =(Page<FavoriteQuestion>)favoriteQuestionList;
        return new PageBean(p.getTotal(),p.getResult());
    }

    @Override
    public void removeById(Integer id) {
        favouriteMapper.deleteById(id);
    }
}

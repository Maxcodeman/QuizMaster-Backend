package com.ncu.quiz_master_backend.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ncu.quiz_master_backend.entity.FavoriteQuestion;
import com.ncu.quiz_master_backend.entity.PageBean;
import com.ncu.quiz_master_backend.entity.WrongQuestion;
import com.ncu.quiz_master_backend.mapper.WrongCollectionMapper;
import com.ncu.quiz_master_backend.service.WrongCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WrongCollectionServiceImpl implements WrongCollectionService {
    @Autowired
    private WrongCollectionMapper wrongCollectionMapper;
    @Override
    public PageBean listAll(Integer page, Integer pageSize, Integer userId, Integer questionId) {
        PageHelper.startPage(page,pageSize);
        List<WrongQuestion> wrongQuestionList = wrongCollectionMapper.selectAll(userId,questionId);
        Page<WrongQuestion> p =(Page<WrongQuestion>)wrongQuestionList;
        return new PageBean(p.getTotal(),p.getResult());
    }
    @Override
    public void removeById(Integer id) {
        wrongCollectionMapper.deleteById(id);
    }
}

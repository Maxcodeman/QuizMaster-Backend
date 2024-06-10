package com.ncu.quiz_master_backend.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ncu.quiz_master_backend.entity.Category;
import com.ncu.quiz_master_backend.entity.PageBean;
import com.ncu.quiz_master_backend.entity.Question;
import com.ncu.quiz_master_backend.mapper.CategoryMapper;
import com.ncu.quiz_master_backend.mapper.QuestionMapper;
import com.ncu.quiz_master_backend.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author max
 * @since 2023-12-04
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public PageBean listAll(Integer page, Integer pageSize, String categoryName) {
        PageHelper.startPage(page,pageSize);
        List<Category> questionList = categoryMapper.selectAll(categoryName);
        Page<Category> p = (Page<Category>) questionList;
        return new PageBean(p.getTotal(),p.getResult());
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeById(Integer id) {
        categoryMapper.deleteById(id);
        //删除分类下的题目
        questionMapper.deleteByCategoryId(id);
        //记录分类下还剩多少题目
        int cnt=questionMapper.selectCountByCategoryId(id);
        //更新该分类的题目数量字段
        categoryMapper.updateForQuestionCount(cnt,id);
    }
}

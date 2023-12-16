package com.ncu.intell_learning_sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ncu.intell_learning_sys.entity.Question;
import com.ncu.intell_learning_sys.mapper.QuestionMapper;
import com.ncu.intell_learning_sys.service.IQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author max
 * @since 2023-12-04
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public Question selectById(Long id) {
        return questionMapper.selectById(id);
    }

    @Override
    public Map<String, Object> selectByKeyword(String keyword,Integer typeId,Integer categoryId, Integer pageNo, Integer pageSize) {
        Page<Question> page=Page.of(pageNo,pageSize);
        page.addOrder(new OrderItem("question_id",true));

        LambdaQueryWrapper<Question> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(keyword!=null){
            lambdaQueryWrapper.like(Question::getQuestionContent,keyword);
        }

        if(typeId!=null){
            lambdaQueryWrapper.eq(Question::getQuestionType,typeId);
        }
        if(categoryId!=null){
            lambdaQueryWrapper.eq(Question::getQuestionCategory,categoryId);
        }

        questionMapper.selectPage(page,lambdaQueryWrapper);
        List<Question> questionList=page.getRecords();

        Map<String,Object> res=new HashMap<>();
        res.put("list",questionList);
        res.put("total",page.getTotal());
        return res;
    }
}

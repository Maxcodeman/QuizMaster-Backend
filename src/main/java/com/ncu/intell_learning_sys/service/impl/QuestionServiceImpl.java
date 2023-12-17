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

    @Override
    public Integer deleteByIds(List<Long> ids) {
        return questionMapper.deleteBatchIds(ids);
    }

    @Override
    public Integer editById(Long id, Integer type, Integer category,String content,String answer) {
        Question q=questionMapper.selectById(id);
        if(type!=null&&type!=q.getQuestionType()){
            q.setQuestionType(type);
        }
        if(category!=null&&category!=q.getQuestionCategory()){
            q.setQuestionCategory(category);
        }
        if(content!=null&&!content.equals(q.getQuestionContent())){
            q.setQuestionContent(content);
        }
        if(answer!=null&&!answer.equals(q.getQuestionAnswer())){
            q.setQuestionAnswer(answer);
        }
        return questionMapper.updateById(q);
    }

    @Override
    public Integer addOne(Integer type, Integer category, String content, String answer) {
        Question q= new Question();
        q.setQuestionType(type).setQuestionCategory(category).setQuestionContent(content).setQuestionAnswer(answer);
        return questionMapper.insert(q);
    }
}

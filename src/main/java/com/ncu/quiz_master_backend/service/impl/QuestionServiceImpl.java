package com.ncu.quiz_master_backend.service.impl;

import com.ncu.quiz_master_backend.entity.PageBean;
import com.ncu.quiz_master_backend.entity.Question;
import com.ncu.quiz_master_backend.mapper.CategoryMapper;
import com.ncu.quiz_master_backend.mapper.QuestionMapper;
import com.ncu.quiz_master_backend.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.web.multipart.MultipartFile;

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
public class QuestionServiceImpl implements IQuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public PageBean listAll(Integer page, Integer pageSize, String questionDesc, Integer categoryId, Integer type) {
        PageHelper.startPage(page,pageSize);
        List<Question> questionList = questionMapper.selectAll(questionDesc,categoryId,type);
        Page<Question> p = (Page<Question>) questionList;
        return new PageBean(p.getTotal(),p.getResult());
    }

    @Override
    public void removeById(List<Integer> ids) {
        questionMapper.deleteById(ids);
        //删完题目后更新分类表中对应的题目数量
        for(Integer id:ids){
            int cnt = questionMapper.selectCountByCategoryId(id);
            categoryMapper.updateForQuestionCount(cnt,id);
        }

    }

    @Override
    public List<Question> addOne(Question question) {
        return questionMapper.insert(question);
    }

    @Override
    public Question getById(Integer id) {
        return questionMapper.selectById(id);
    }

    @Override
    public void modify(Question question) {
        questionMapper.update(question);
    }

    @Override
    public void upload(MultipartFile file) {
        /**
         * TODO 在这里处理接收到的file文件
         */
    }
}

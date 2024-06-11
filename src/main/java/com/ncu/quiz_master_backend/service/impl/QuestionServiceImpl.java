package com.ncu.quiz_master_backend.service.impl;

import com.ncu.quiz_master_backend.entity.Category;
import com.ncu.quiz_master_backend.entity.PageBean;
import com.ncu.quiz_master_backend.entity.Question;
import com.ncu.quiz_master_backend.mapper.CategoryMapper;
import com.ncu.quiz_master_backend.mapper.QuestionMapper;
import com.ncu.quiz_master_backend.service.IQuestionService;
import com.ncu.quiz_master_backend.utils.HandleFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
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
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeById(List<Integer> ids) {
        questionMapper.deleteById(ids);
        //删完题目后更新分类表中对应的题目数量
        for(int i=1;i<=3;i++){
            //获取该分类的题目总数
            int cnt = questionMapper.selectCountByCategoryId(i);
            //更新分类表的题目数
            categoryMapper.updateForQuestionCount(cnt,i);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addOne(Question question) {
        questionMapper.insert(question);
        int id=question.getCategoryId();
        int cnt = questionMapper.selectCountByCategoryId(id);
        categoryMapper.updateForQuestionCount(cnt,id);
    }

    @Override
    public Question getById(Integer id) {
        return questionMapper.selectById(id);
    }

    @Override
    public void modify(Question question) {
        questionMapper.update(question);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void upload(MultipartFile file) throws IOException {
        /**
         * TODO 在这里处理接收到的file文件
         */
        InputStream inputStream = file.getInputStream();
        List<Question> questionList=HandleFile.excelReader(inputStream);
        for(Question question:questionList){
            //逐项插入
            questionMapper.insert(question);
        }
        for(int i=1;i<=3;i++){
            //获取该分类的题目总数
            int cnt = questionMapper.selectCountByCategoryId(i);
            //更新分类表的题目数
            categoryMapper.updateForQuestionCount(cnt,i);
        }
    }
}

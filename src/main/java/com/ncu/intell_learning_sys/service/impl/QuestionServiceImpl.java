package com.ncu.intell_learning_sys.service.impl;

import com.ncu.intell_learning_sys.entity.Question;
import com.ncu.intell_learning_sys.mapper.QuestionMapper;
import com.ncu.intell_learning_sys.service.IQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}

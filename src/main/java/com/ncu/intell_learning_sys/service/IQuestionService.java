package com.ncu.intell_learning_sys.service;

import com.ncu.intell_learning_sys.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author max
 * @since 2023-12-04
 */
public interface IQuestionService extends IService<Question> {

    Question selectById(Long id);

    Map<String, Object> selectByKeyword(String keyword, Integer typeId,Integer categoryId,Integer pageNo, Integer pageSize);
}

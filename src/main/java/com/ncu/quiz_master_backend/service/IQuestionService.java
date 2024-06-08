package com.ncu.quiz_master_backend.service;

import com.ncu.quiz_master_backend.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
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

    Integer deleteByIds(List<Long> ids);

    Integer editById(Long id, Integer type, Integer category, String content,String answer);

    Integer addOne(Integer type, Integer category, String content, String answer);
}

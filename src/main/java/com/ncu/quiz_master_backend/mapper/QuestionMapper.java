package com.ncu.quiz_master_backend.mapper;

import com.ncu.quiz_master_backend.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author max
 * @since 2023-12-04
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

}
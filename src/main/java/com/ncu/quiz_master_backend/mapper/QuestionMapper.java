package com.ncu.quiz_master_backend.mapper;

import com.ncu.quiz_master_backend.entity.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author max
 * @since 2023-12-04
 */
@Mapper
public interface QuestionMapper {

    List<Question> selectAll(String questionDesc, Integer categoryId, Integer type);

    void deleteById(List<Integer> ids);
    @Insert("insert into tb_question(question_desc,option_a,option_b,option_c,option_d,answer,category_id,type) " +
            "values(#{questionDesc},#{optionA},#{optionB}," +
            "#{optionC},#{optionD},#{answer},#{categoryId},#{type}) ")
    void insert(Question question);

    @Select("select * from tb_question where question_id = #{id}")
    Question selectById(Integer id);

    void update(Question question);
    @Delete("delete from tb_question where category_id = #{id}")
    void deleteByCategoryId(Integer id);

    @Select("select count(*) from tb_question where category_id = #{id}")
    int selectCountByCategoryId(Integer id);
}

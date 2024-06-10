package com.ncu.quiz_master_backend.mapper;

import com.ncu.quiz_master_backend.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
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
    @Insert("insert into question(question_desc,option_a,option_b,optiob_c,option_d,answer,category_id,type) " +
            "values(#{questionDesc},#{optionA},#{optionB}," +
            "#{optiobC},#{optionD},#{answer},#{categoryId},#{type}) ")
    List<Question> insert(Question question);

    @Select("select * from question where id = #{id}")
    Question selectById(Integer id);

    void update(Question question);
    @Delete("delete from tb_question where category_id = #{id}")
    void deleteByCategoryId(Integer id);

    @Select("select count(*) from tb_question where category_id = #{id}")
    int selectCountByCategoryId(Integer id);
}

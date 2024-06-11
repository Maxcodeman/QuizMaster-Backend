package com.ncu.quiz_master_backend.mapper;

import com.ncu.quiz_master_backend.entity.Category;
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
public interface CategoryMapper{

    List<Category> selectAll(String categoryName);
    @Delete("delete from tb_category where category_id=#{id}")
    void deleteById(Integer id);

    @Update("update tb_category set question_count=#{cnt} where category_id = #{id}")
    void updateForQuestionCount(Integer cnt,Integer id);

    @Insert("insert into tb_category (category_name, question_count) " +
            "values (#{categoryName},#{questionCount})")
    void insert(Category category);

    @Select("select * from tb_category where category_id=#{id}")
    Category selectById(Integer id);
    @Update("update tb_category set category_name=#{categoryName} where category_id =#{categoryId}")
    void update(Category category);
}

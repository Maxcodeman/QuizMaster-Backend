package com.ncu.quiz_master_backend.mapper;

import com.ncu.quiz_master_backend.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapperForUsers {

    @Select("select * from tb_category where category_id = #{categoryId}")
    Category getCategoryInfoByCategoryId(Integer categoryId);

    @Select("select * from tb_category")
    List<Category> getAllCategories();
}

package com.ncu.quiz_master_backend.mapper;

import com.ncu.quiz_master_backend.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

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
    @Delete("delete from tb_category where id=#{id}")
    void deleteById(Integer id);

    @Update("update tb_category set questionCount=#{cnt} where id = #{id}")
    void updateForQuestionCount(Integer cnt,Integer id);
}

package com.ncu.quiz_master_backend.mapper;

import com.ncu.quiz_master_backend.entity.FavoriteQuestion;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FavouriteMapper {
    List<FavoriteQuestion> selectAll(Integer userId, Integer questionId);
    @Delete("delete from tb_favorite where favorite_id=#{id}")
    void deleteById(Integer id);
}

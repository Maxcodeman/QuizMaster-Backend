package com.ncu.quiz_master_backend.mapper;

import com.ncu.quiz_master_backend.entity.WrongQuestion;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WrongCollectionMapper {
    List<WrongQuestion> selectAll(Integer userId, Integer questionId);
    @Delete("delete from tb_wrong where wrong_id=#{id}")
    void deleteById(Integer id);
}

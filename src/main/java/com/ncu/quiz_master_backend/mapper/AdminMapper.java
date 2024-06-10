package com.ncu.quiz_master_backend.mapper;

import com.ncu.quiz_master_backend.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 管理员Mapper层
 */
@Mapper
public interface AdminMapper {
    @Select("select * from tb_admin where admin_id=#{id}")
    Admin queryById(Integer id);

    @Update("update tb_admin set admin_name=#{adminName},mobile=#{mobile},password=#{password} where admin_id=#{adminId}")
    void update(Admin admin);

    @Delete("delete from tb_admin where admin_id=#{id}")
    void deleteById(Integer id);
}

package com.ncu.quiz_master_backend.mapper;

import com.ncu.quiz_master_backend.entity.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 管理员Mapper层
 */
@Mapper
public interface AdminMapper {
    @Select("select * from tb_admin where admin_id=#{id}")
    Admin queryById(Integer id);

    void update(Admin admin);

    @Delete("delete from tb_admin where admin_id=#{id}")
    void deleteById(Integer id);

    List<Admin> list(String name,Integer page,Integer pageSize);

    @Insert("insert into tb_admin(admin_name, password, mobile) values(#{adminName},#{password},#{mobile})")
    void insert(Admin admin);
}

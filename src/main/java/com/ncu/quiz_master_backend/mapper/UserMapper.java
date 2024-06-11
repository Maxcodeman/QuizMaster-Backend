package com.ncu.quiz_master_backend.mapper;

import com.ncu.quiz_master_backend.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> selectAll(String username, String mobile);


    void deleteById(List<Integer> ids);
    @Insert("insert into tb_user (username, password, state, create_time, update_time, avatar, mobile) " +
            "values (#{username},#{password},#{state},#{createTime},#{updateTime},#{avatar},#{mobile})")
    void insert(User user);
    @Select("select * from tb_user where user_id=#{id}")
    User selectById(Integer id);

    void update(User user);
    @Update("update tb_user set state=#{state} where user_id=#{userId}")
    void updateState(User user);
}

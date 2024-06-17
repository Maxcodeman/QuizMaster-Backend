package com.ncu.quiz_master_backend.mapper;



import com.ncu.quiz_master_backend.entity.User;
import com.ncu.quiz_master_backend.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapperForUsers {

    @Update("update tb_user set password = #{newPassword} where user_id = #{userId} and password = #{oldPassword}")
    void updatePassword(Integer userId, String oldPassword, String newPassword);

    @Select("select user_id, username, avatar, mobile from tb_user where user_id = #{userId}")
    UserInfo selectById(Integer userId);


    void updateInfo(Integer userId, String username, String avatar, String mobile);

    @Select("select * from tb_user where user_id = #{userId}")
    User queryById(Integer userId);

    @Select("select password from tb_user where user_id = #{userId}")
    String queryPasswordById(Integer userId);
}

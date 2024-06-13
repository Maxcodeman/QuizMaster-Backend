package com.ncu.quiz_master_backend.mapper;

import com.ncu.quiz_master_backend.entity.Message;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface MessageMapper {


    List<Message> selectMessageByConditions(Integer adminId,
                                            LocalDateTime startTime,
                                            LocalDateTime endTime
                                            );

    @Delete("delete from tb_message where message_id = #{id}")
    void deleteById(Integer id);

    @Insert("INSERT INTO tb_message(admin_id, message_content) VALUE (#{adminId},#{messageContent})")
    void insertMessage(Integer adminId, String messageContent);

    @Select("select * from tb_message where message_id = #{id}")
    Message selectById(Integer id);

    @Update("update tb_message set message_content=#{messageContent}, view=#{view} where message_id = #{messageId}")
    void updateMessage(Integer messageId, String messageContent, Integer view);
}

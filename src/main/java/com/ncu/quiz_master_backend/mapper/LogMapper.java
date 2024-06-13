package com.ncu.quiz_master_backend.mapper;

import com.ncu.quiz_master_backend.entity.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface LogMapper {
    //插入日志数据
    @Insert("insert into tb_log (admin_id,create_time,class_name, method_name, method_params, return_value) " +
            "values (#{adminId}, #{createTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue});")
    void insert(Log log);

    List<Log> list(Integer adminId, LocalDateTime startTime,LocalDateTime endTime);
}

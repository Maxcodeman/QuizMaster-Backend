package com.ncu.quiz_master_backend.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{
    private Integer userId;

    private String username;

    private String password;

    //用户状态
    private Boolean state;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer preference;

    //用户头像（URL地址）
    private String avatar;
}

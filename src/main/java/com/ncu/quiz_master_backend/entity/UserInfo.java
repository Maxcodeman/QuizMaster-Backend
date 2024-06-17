package com.ncu.quiz_master_backend.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private Integer userId;
    private String username;
    private String avatar;
    private String mobile;
}

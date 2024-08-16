package com.nutrihub.user.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private int user_pk;
    private String user_id;
    private String user_pw;
    private String sex;
    private Date Created_at;
}

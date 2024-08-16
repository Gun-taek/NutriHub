package com.nutrihub.admin.dto;


import lombok.Data;

import java.util.Date;

@Data
public class AdminDto {
    private int admin_pk;
    private String admin_id;
    private String admin_pw;
    private String phone;
    private String email;
    private String sex;
    private Date Created_at;
}

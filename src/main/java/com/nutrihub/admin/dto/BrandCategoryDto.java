package com.nutrihub.admin.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BrandCategoryDto {
    private int brand_pk;
    private String brand_korean_name;
    private String brand_english_name;
    private String brand_logo;
    private String status;
    private Date created_at;
}

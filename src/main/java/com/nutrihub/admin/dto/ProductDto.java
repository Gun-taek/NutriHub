package com.nutrihub.admin.dto;

import lombok.Data; // lombok Data 라이브러리 = getter,setter 생략
import java.util.Date;

@Data
public class ProductDto {
    private int product_pk;
    private int product_category_pk;
    private String product_name;
    private String representative_image;
    private String detailed_image1;
    private String detailed_image2;
    private String detailed_image3;
    private String content;
    private int price;
    private int sale_percent;
    private int sale_price;
    private int stock;
    private Date reg_date;
}
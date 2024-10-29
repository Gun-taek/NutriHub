package com.nutrihub.admin.mapper;


import com.nutrihub.admin.dto.ProductDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminSqlMapper {

    public void ProductRegister(ProductDto productDto);
}

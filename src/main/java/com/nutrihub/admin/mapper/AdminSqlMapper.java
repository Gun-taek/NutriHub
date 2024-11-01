package com.nutrihub.admin.mapper;
import com.nutrihub.admin.dto.ProductCategoryDto;
import com.nutrihub.admin.dto.ProductDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminSqlMapper{

    public void ProductRegister(ProductDto productDto);

    public List<ProductCategoryDto> ProductCategoryList();
}

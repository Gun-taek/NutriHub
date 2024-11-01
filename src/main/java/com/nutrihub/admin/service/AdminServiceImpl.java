package com.nutrihub.admin.service;

import com.nutrihub.admin.dto.ProductCategoryDto;
import com.nutrihub.admin.dto.ProductDto;
import com.nutrihub.admin.mapper.AdminSqlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl {

    @Autowired
    private AdminSqlMapper adminSqlMapper;
    public void ProductRegister(ProductDto productDto){
        adminSqlMapper.ProductRegister(productDto);
    }

    public List<ProductCategoryDto> ProductCategoryList(){
        return adminSqlMapper.ProductCategoryList();
    }

}


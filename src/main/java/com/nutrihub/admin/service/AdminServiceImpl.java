package com.nutrihub.admin.service;

import com.nutrihub.admin.dto.ProductDto;
import com.nutrihub.admin.mapper.AdminSqlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl {

    @Autowired
    private AdminSqlMapper adminSqlMapper;
    public void ProductRegister(ProductDto productDto){
        adminSqlMapper.ProductRegister(productDto);
    }


}


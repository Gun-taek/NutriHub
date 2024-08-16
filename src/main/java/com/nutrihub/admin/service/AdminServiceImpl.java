package com.nutrihub.admin.service;

import com.nutrihub.admin.mapper.AdminSqlMapper;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl {
    private final AdminSqlMapper adminSqlMapper;
    public AdminServiceImpl(AdminSqlMapper adminSqlMapper){
        this.adminSqlMapper = adminSqlMapper;
    }
}

package com.nutrihub.admin.controller;


import com.nutrihub.admin.service.AdminServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/NutriHub/Admin/*")
public class AdminController {
    private final AdminServiceImpl adminService;
    public AdminController(AdminServiceImpl adminService){
        this.adminService = adminService;
    }

    // 관리자 메인 페이지 포워딩
    @RequestMapping("MainPage")
    public String MainPage(){
        return "/admin/adminMainPage";
    }

    @RequestMapping("ProductRegisterPage")
    public String ProductRegisterPage () {
        return "/admin/adminProductRegisterPage";
    }
}

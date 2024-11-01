package com.nutrihub    .admin.controller;
import com.nutrihub.admin.dto.ProductDto;
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

    //관리 카테고리 종류 리스트로 받아오기
    // 상품 등록 요청
    @RequestMapping("ProductRegister")
    public String ProductRegister(ProductDto productDto){
        adminService.ProductRegister(productDto);

//        System.out.println(productDto.getProduct_name()); // 테스트 잘 되는지.
        return "/admin/adminMainPage";
    }



}

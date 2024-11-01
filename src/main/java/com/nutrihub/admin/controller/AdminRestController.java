    package com.nutrihub.admin.controller;
    import com.nutrihub.admin.dto.ProductCategoryDto;
    import com.nutrihub.admin.dto.ProductDto;
    import com.nutrihub.admin.service.AdminServiceImpl;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    import java.util.List;

    @RestController
    @RequestMapping("/NutriHub/Admin/API/*")
    public class AdminRestController {
        private final AdminServiceImpl adminService;
        public AdminRestController(AdminServiceImpl adminService){this.adminService = adminService;}

        // 상품 카테고리 List 반환 Restful
        @RequestMapping("ProductCategoryList")
        public List<ProductCategoryDto> ProductCategoryList(){
            return adminService.ProductCategoryList();
        }

    }

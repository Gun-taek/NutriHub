package com.nutrihub    .admin.controller;
import com.nutrihub.admin.dto.ProductCategoryDto;
import com.nutrihub.admin.dto.ProductDto;
import com.nutrihub.admin.dto.RefundAddressDto;
import com.nutrihub.admin.dto.ShippingAddressDto;
import com.nutrihub.admin.service.AdminServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

import static org.apache.coyote.http11.Constants.a;

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
    public String ProductRegisterPage (Model model) {
        // 카테고리 리스트 Model 통해 전달
        List<ProductCategoryDto> CategoryList = adminService.ProductCategoryList();
        model.addAttribute("productCategoryList", CategoryList);
        return "/admin/adminProductRegisterPage";
    }
    //관리 카테고리 종류 리스트로 받아오기


    // 상품 등록 요청 보내기
    @RequestMapping("ProductRegister")
    public String ProductRegister(
            @ModelAttribute ShippingAddressDto shippingAddressDto, // ShippingAddressDto를 Model로 전달
            @ModelAttribute RefundAddressDto refundAddressDto,
            @ModelAttribute ProductDto productDto,
            @RequestParam("represent_image") MultipartFile representativeImage,
            @RequestParam("detailed_images") List<MultipartFile> detailedImages) throws Exception{
        // 서버에 이미지 파일을 저장할 경로
        String imagePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\public\\userUploadImages";

        // 대표 이미지 처리
        if (!representativeImage.isEmpty()) {
            UUID uuid = UUID.randomUUID(); // 대표 이미지용 UUID 생성
            String representFileName = uuid + "_" + representativeImage.getOriginalFilename();
            File representFile = new File(imagePath, representFileName);

            // 대표 이미지 파일 저장
            representativeImage.transferTo(representFile);
            productDto.setRepresentative_image("/public/userUploadImages/" + representFileName);
        }

        // 상세 이미지 처리
        for (int i = 0; i < detailedImages.size() && i < 3; i++) {
            MultipartFile detailedImage = detailedImages.get(i);

            if (!detailedImage.isEmpty()) {
                UUID detailedUuid = UUID.randomUUID(); // 상세 이미지마다 고유 UUID 생성
                String detailedFileName = detailedUuid + "_" + detailedImage.getOriginalFilename();
                File detailedFile = new File(imagePath, detailedFileName);

                // 상세 이미지 파일 저장
                detailedImage.transferTo(detailedFile);

                // ProductDto에 파일 경로 설정
                switch (i) {
                    case 0:
                        productDto.setDetailed_image1("/public/userUploadImages/" + detailedFileName);
                        break;
                    case 1:
                        productDto.setDetailed_image2("/public/userUploadImages/" + detailedFileName);
                        break;
                    case 2:
                        productDto.setDetailed_image3("/public/userUploadImages/" + detailedFileName);
                        break;
                }
            }
        }
        // 작업 후 리턴 (업로드 완료 후 페이지로 이동)
        // DTO 설정 후 상품 등록
        adminService.ProductRegister(productDto, shippingAddressDto, refundAddressDto);
        return "/admin/adminMainPage";
    }

}




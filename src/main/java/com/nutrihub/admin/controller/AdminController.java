package com.nutrihub.admin.controller;
import com.nutrihub.admin.dto.*;
import com.nutrihub.admin.service.AdminServiceImpl;
//import org.springframework.security.core.parameters.P;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import static org.apache.coyote.http11.Constants.a;
@Controller
@RequestMapping("/NutriHub/Admin/*")
public class AdminController {
    private final AdminServiceImpl adminService;

    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    // 관리자 메인 페이지 포워딩
    @RequestMapping("MainPage")
    public String MainPage() {
        return "/admin/adminMainPage";
    }

    @RequestMapping("ProductRegisterPage")
    public String ProductRegisterPage(Model model) {
        // 카테고리 리스트 Model 통해 전달
        List<ProductCategoryDto> CategoryList = adminService.ProductCategoryList();
        model.addAttribute("productCategoryList", CategoryList);

        List<BrandCategoryDto> BrandCategoryList = adminService.BrandCategoryList();
        model.addAttribute("brandCategoryList",BrandCategoryList);

        return "/admin/adminProductRegisterPage";
    }
    //관리 카테고리 종류 리스트로 받아오기
    // 상품 등록 요청 보내기
    @RequestMapping("BrandRegister")
    public ResponseEntity<?> registerBrand(
            @RequestParam("brand_korean_name") String brand_korean_name,
            @RequestParam("brand_english_name") String brand_english_name,
            @RequestParam("brand_image") MultipartFile brand_image) throws IOException {
        BrandCategoryDto brandCategoryDto = new BrandCategoryDto();
        brandCategoryDto.setBrand_korean_name(brand_korean_name);
        brandCategoryDto.setBrand_english_name(brand_english_name);

        String rootPath = new File("src/main/resources/static").getAbsolutePath();
        String imagePath = rootPath + "/public/userUploadImages";

        String brandImagePath = rootPath + "/public/image/adminProductRegisterPage/brandLogo";


        if (!brand_image.isEmpty()) {
            UUID uuid = UUID.randomUUID();
            String brandImageFileName = uuid + "_" + brand_image.getOriginalFilename();
            File brandImageFile = new File(brandImagePath, brandImageFileName);
            brand_image.transferTo(brandImageFile);
            brandCategoryDto.setBrand_logo("/public/image/brandLogo/" + brandImageFileName);
        }

        try { // 성공 시  Service로 BrandRegister 호출
            adminService.BrandRegister(brandCategoryDto);
            return ResponseEntity.ok().body("{\"message\":\"브랜드 등록이 완료되었습니다.\"}");
            // JSON 형태로 클라이언트에 200 성공 메세지 반환


        } catch (Exception e) { // 에러 발생 시
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\":\"브랜드 등록 중 오류가 발생했습니다.\"}");
        }
    }
    @RequestMapping("ProductRegister")
    public ResponseEntity<Map<String, Object>> ProductRegister(
            @RequestParam("product_category_pk") int product_category_pk,
            @RequestParam("product_name") String name,
            @RequestParam("price") int price,
            @RequestParam("sale_percent") int sale_percent,
            @RequestParam("sale_price") int sale_price,
            @RequestParam("content") String content,
            @RequestParam("stock") int stock,
            @RequestParam("represent_image") MultipartFile representativeImage,
            @RequestParam("detailed_images") List<MultipartFile> detailedImages,

            @RequestParam("shipping_address_name") String shipping_address_name,
            @RequestParam("shipping_post_number") String shipping_post_number,
            @RequestParam("shipping_representative_address") String shipping_representative_address,
            @RequestParam("shipping_detail_address") String shipping_detail_address,
            @RequestParam("shipping_phone_number") String shipping_phone_number,
            @RequestParam("shipping_extra_phone_number") String shipping_extra_phone_number,

            @RequestParam("refund_address_name") String refund_address_name,
            @RequestParam("refund_address_post_number") String refund_address_post_number,
            @RequestParam("refund_representative_address") String refund_representative_address,
            @RequestParam("refund_detail_address") String refund_detail_address,
            @RequestParam("refund_phone_number") String refund_phone_number,
            @RequestParam("refund_extra_phone_number") String refund_extra_phone_number
    ) throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setProduct_category_pk(product_category_pk);
        productDto.setProduct_name(name);
        productDto.setPrice(price);
        productDto.setSale_percent(sale_percent);
        productDto.setSale_price(sale_price);
        productDto.setContent(content);
        productDto.setStock(stock);

        ShippingAddressDto shippingAddressDto = new ShippingAddressDto();
        shippingAddressDto.setShipping_address_name(shipping_address_name);
        shippingAddressDto.setShipping_post_number(shipping_post_number);
        shippingAddressDto.setShipping_representative_address(shipping_representative_address);
        shippingAddressDto.setShipping_detail_address(shipping_detail_address);
        shippingAddressDto.setShipping_phone_number(shipping_phone_number);
        shippingAddressDto.setShipping_extra_phone_number(shipping_extra_phone_number);

        RefundAddressDto refundAddressDto = new RefundAddressDto();
        refundAddressDto.setRefund_address_name(refund_address_name);
        refundAddressDto.setRefund_address_post_number(refund_address_post_number);
        refundAddressDto.setRefund_representative_address(refund_representative_address);
        refundAddressDto.setRefund_detail_address(refund_detail_address);
        refundAddressDto.setRefund_phone_number(refund_phone_number);
        refundAddressDto.setRefund_extra_phone_number(refund_extra_phone_number);
        // 서버에 이미지 파일을 저장할 경로
        String rootPath = new File("src/main/resources/static").getAbsolutePath();
        String imagePath = rootPath + "/public/userUploadImages";

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
        // 서비스에 sql insert 작업 요청
        adminService.ProductRegister(productDto, shippingAddressDto, refundAddressDto);
        // ResponseEntity를 사용하여 JSON 형식으로 응답을 반환
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Product registered successfully");
        response.put("status", "success");
        response.put("redirectUrl", "/admin/adminMainPage");

        // 응답을 JSON 형태로 반환 (HTTP 200 OK)
        return ResponseEntity.ok(response);
    }
    @RequestMapping("AdminBrandManagement")
    public String AdminBrandManagement(){
        return "/admin/adminBrandManagement";
    }
}




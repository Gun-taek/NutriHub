package com.nutrihub.admin.service;

import com.nutrihub.admin.dto.ProductCategoryDto;
import com.nutrihub.admin.dto.ProductDto;
import com.nutrihub.admin.dto.RefundAddressDto;
import com.nutrihub.admin.dto.ShippingAddressDto;
import com.nutrihub.admin.mapper.AdminSqlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServiceImpl {

    @Autowired
    private AdminSqlMapper adminSqlMapper;
    public void ProductRegister(ProductDto productDto, ShippingAddressDto shippingAddressDto, RefundAddressDto refundAddressDto){
        // 상품 등록
        adminSqlMapper.ProductRegister(productDto);  // 이때 productDto에 자동으로 product_pk가 설정됩니다.

        // 상품 고유번호 설정
        int productPk = productDto.getProduct_pk();

        shippingAddressDto.setProduct_pk(productPk); // 외래키 설정
        adminSqlMapper.ShippingAddressRegister(shippingAddressDto);  // 출고지 등록

        refundAddressDto.setProduct_pk(productPk); // 외래키 설정
        adminSqlMapper.RefundAddressRegister(refundAddressDto);  // 반송지 등록


        System.out.println(productPk);
    }
    public void ShippingAddressRegister(ShippingAddressDto shippingAddressDto) {adminSqlMapper.ShippingAddressRegister(shippingAddressDto);}
    public void RefundAddressRegister(RefundAddressDto refundAddressDto){adminSqlMapper.RefundAddressRegister(refundAddressDto);}


    // ------------데이터 가져오기 --------------

    public int ProductMaxPk(){return adminSqlMapper.ProductMaxPk();}
    public List<ProductCategoryDto> ProductCategoryList(){
        return adminSqlMapper.ProductCategoryList();
    }

}


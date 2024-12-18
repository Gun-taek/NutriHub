package com.nutrihub.admin.mapper;
import com.nutrihub.admin.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminSqlMapper{

    // ------------------------- insert 문 -------------------------
    public void BrandRegister(BrandCategoryDto brandCategoryDto);
    public void ProductRegister(ProductDto productDto);
    public void ShippingAddressRegister(ShippingAddressDto shippingAddressDto);
    public void RefundAddressRegister(RefundAddressDto refundAddressDto);
    // --------------------------------------------------------------

    public int ProductMaxPk();
    public List<ProductCategoryDto> ProductCategoryList();
    public List<BrandCategoryDto> BrandCategoryList();
}

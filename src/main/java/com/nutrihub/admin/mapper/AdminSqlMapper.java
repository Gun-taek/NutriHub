package com.nutrihub.admin.mapper;
import com.nutrihub.admin.dto.ProductCategoryDto;
import com.nutrihub.admin.dto.ProductDto;
import com.nutrihub.admin.dto.RefundAddressDto;
import com.nutrihub.admin.dto.ShippingAddressDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminSqlMapper{

    // ------------------------- insert 문 -------------------------
    public void ProductRegister(ProductDto productDto);
    public void ShippingAddressRegister(ShippingAddressDto shippingAddressDto);
    public void RefundAddressRegister(RefundAddressDto refundAddressDto);
    // --------------------------------------------------------------

    public int ProductMaxPk();
    public List<ProductCategoryDto> ProductCategoryList();


}

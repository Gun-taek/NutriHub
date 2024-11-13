package com.nutrihub.admin.dto;
import lombok.Data;
@Data
public class ShippingAddressDto {
    private int shipping_address_pk;
    private int product_pk;
    private String shipping_address_name;
    private String shipping_post_number;
    private String shipping_representative_address;
    private String shipping_detail_address;
    private String shipping_phone_number;
    private String shipping_extra_phone_number;
}

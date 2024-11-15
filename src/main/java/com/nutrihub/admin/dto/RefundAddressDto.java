package com.nutrihub.admin.dto;

import lombok.Data;

@Data
public class RefundAddressDto {

    private int refund_address_pk;
    private int product_pk;
    private String refund_address_name;
    private String refund_address_post_number;
    private String refund_representative_address;
    private String refund_detail_address;
    private String refund_phone_number;
    private String refund_extra_phone_number;
}

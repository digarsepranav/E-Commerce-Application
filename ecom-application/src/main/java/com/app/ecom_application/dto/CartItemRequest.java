package com.app.ecom_application.dto;

import lombok.Data;

@Data
public class CartItemRequest {
    private long productId;
    private Integer quantity;
}

package com.nazat.shoppingcart.dto;

import com.nazat.shoppingcart.entities.Order;
import com.nazat.shoppingcart.entities.Product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {
    private Long productId;
    private String productName;
    private int quantity;
    private BigDecimal price;
    private String productBrand;
}

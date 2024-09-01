package com.nazat.shoppingcart.dto;

import com.nazat.shoppingcart.entities.CartItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class CartDto {
    private Long id;
    private BigDecimal totalAmount;
    private Set<CartItemDto> cartItems;

}

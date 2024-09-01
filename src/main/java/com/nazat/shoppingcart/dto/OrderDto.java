package com.nazat.shoppingcart.dto;

import com.nazat.shoppingcart.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;


@Data
public class OrderDto {
    private Long id;
    private Long userId;
    private LocalDate orderDate;
    private BigDecimal totalAmount;
    private OrderStatus orderStatus;

    private Set<OrderItemDto> orderItems;
}

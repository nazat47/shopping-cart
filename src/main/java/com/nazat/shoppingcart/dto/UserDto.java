package com.nazat.shoppingcart.dto;

import com.nazat.shoppingcart.entities.Cart;
import com.nazat.shoppingcart.entities.Order;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private CartDto cart;
    private List<OrderDto> orders;
}

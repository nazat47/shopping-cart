package com.nazat.shoppingcart.service.cart;

import com.nazat.shoppingcart.entities.Cart;
import com.nazat.shoppingcart.entities.User;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);

    void clearCart(Long id);

    BigDecimal getTotalPrice(Long id);

    Cart initializeNewCart(User user);

    Cart getCartByUserId(Long userId);
}

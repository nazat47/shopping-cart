package com.nazat.shoppingcart.service.cart;

import com.nazat.shoppingcart.entities.CartItem;

public interface ICartItemService {
    void addItemToCart(Long cartId, Long productId, int quantuty);

    void removeItemFromCart(Long cartId, Long productId);

    void updateItemQuantity(Long cartId, Long productId, int quantity);

    CartItem getCartItem(Long cartId, Long productId);
}

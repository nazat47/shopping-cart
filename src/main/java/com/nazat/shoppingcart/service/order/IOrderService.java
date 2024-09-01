package com.nazat.shoppingcart.service.order;

import com.nazat.shoppingcart.dto.OrderDto;
import com.nazat.shoppingcart.entities.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);

    OrderDto getOrder(Long orderId);

    List<OrderDto> getUserOrders(Long userId);

    OrderDto convertToOrderDto(Order order);
}

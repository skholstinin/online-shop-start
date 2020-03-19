package com.example.service;


import com.example.entity.Cart;
import com.example.entity.ProductInOrder;
import com.example.entity.User;

import java.util.Collection;

public interface CartService {
    Cart getCart(User user);

    void mergeLocalCart(Collection<ProductInOrder> productInOrders, User user);

    void delete(String itemId, User user);

    void checkout(User user);
}

package com.example.service;


import com.example.entity.ProductInOrder;
import com.example.entity.User;

public interface ProductInOrderService {
    void update(String itemId, Integer quantity, User user);

    ProductInOrder findOne(String itemId, User user);
}

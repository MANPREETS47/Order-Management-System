package com.oms.order.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.oms.order.service.OrderService;
import com.oms.common.lib.events.OrderCreatedEvent;

@RestController
public class OrderserviceController {
    
    @Autowired
    private OrderService orderService;

    @PostMapping("/users/orders")
    public String createOrder(@RequestBody OrderCreatedEvent order) {
        orderService.createOrder(order);
        return "Order created";
    }
}

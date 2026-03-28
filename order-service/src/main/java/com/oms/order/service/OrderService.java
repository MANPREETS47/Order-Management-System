package com.oms.order.service;

import org.springframework.stereotype.Service;
import com.oms.order.kafka.producer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.oms.common.lib.events.OrderCreatedEvent;

@Service
@AllArgsConstructor

public class OrderService {
    
    @Autowired
    private producer producer;

    public void createOrder(OrderCreatedEvent order) {
        producer.sendMessage(order);
    }
}

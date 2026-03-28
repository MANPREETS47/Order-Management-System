package com.oms.inventory.service;

import org.springframework.stereotype.Service;
import com.oms.common.lib.events.PaymentSuccessEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.oms.inventory.kafka.InventoryProducer;

@Service
@RequiredArgsConstructor
public class InventoryService {

    @Autowired
    private InventoryProducer inventoryProducer;

    public void processOrder(PaymentSuccessEvent event) {
        System.out.println("Inventory service processing order: " + event.getOrderId());
        inventoryProducer.sendMessage(event);
    }
}
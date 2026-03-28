package com.oms.inventory.service;

import org.springframework.stereotype.Service;
import com.oms.common.lib.events.PaymentSuccessEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.oms.inventory.kafka.InventoryProducer;
import com.oms.common.lib.idempotency.IdempotencyService;

@Service
@RequiredArgsConstructor
public class InventoryService {

    @Autowired
    private InventoryProducer inventoryProducer;
    private final IdempotencyService idempotencyService;

    public void processOrder(PaymentSuccessEvent event) {
        String key = "inventory_service:" + event.getOrderId();
        if (idempotencyService.isProcessed(key)) {
            System.out.println("Inventory already processed for order: " + event.getOrderId());
            return;
        }
        System.out.println("Inventory service processing order: " + event.getOrderId() + " for user: " + event.getUserEmail());
        idempotencyService.markProcessed(key);
        inventoryProducer.sendMessage(event);
    }

}
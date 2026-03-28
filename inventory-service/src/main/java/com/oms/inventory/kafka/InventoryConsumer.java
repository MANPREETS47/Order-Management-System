package com.oms.inventory.kafka;

import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;
import com.oms.common.lib.events.PaymentSuccessEvent;
import com.oms.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class InventoryConsumer {
    
    @Autowired
    private InventoryService inventoryService;


    @KafkaListener(topics="payment-topic",groupId="inventory-group")
    public void consume(PaymentSuccessEvent event) {
        System.out.println("Inventory Consumer received payment event for order: " + event.getOrderId());
        inventoryService.processOrder(event);
    }
}


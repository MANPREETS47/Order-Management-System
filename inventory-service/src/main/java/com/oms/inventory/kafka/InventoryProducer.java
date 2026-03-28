package com.oms.inventory.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class InventoryProducer {
    

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public InventoryProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private final String TOPIC_NAME = "inventory-topic";

    public void sendMessage(Object message) {
        kafkaTemplate.send(TOPIC_NAME, message);
    }
}

package com.oms.order.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class producer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public producer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private final String TOPIC_NAME = "order-topic";

    public void sendMessage(Object message) {
        kafkaTemplate.send(TOPIC_NAME, message);
    }
}

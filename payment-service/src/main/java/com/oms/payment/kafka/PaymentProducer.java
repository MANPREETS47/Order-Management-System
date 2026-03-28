package com.oms.payment.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PaymentProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private final String TOPIC_NAME = "payment-topic";

    public void sendMessage(Object message) {
        kafkaTemplate.send(TOPIC_NAME, message);
    }
}


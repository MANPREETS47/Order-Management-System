package com.oms.payment.kafka;

import com.oms.common.lib.events.OrderCreatedEvent;
import com.oms.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentConsumer {

    private final PaymentService paymentService;

    @KafkaListener(topics = "order-topic", groupId = "payment-group")
    public void consume(OrderCreatedEvent event) {
        paymentService.processPayment(event);
    }
}
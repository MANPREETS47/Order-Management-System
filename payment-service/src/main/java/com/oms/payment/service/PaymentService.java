package com.oms.payment.service;

import com.oms.common.lib.events.OrderCreatedEvent;
import com.oms.common.lib.events.PaymentSuccessEvent;
import com.oms.payment.kafka.PaymentProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.oms.common.lib.idempotency.IdempotencyService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentProducer paymentProducer;
    private final IdempotencyService idempotencyService;

    public void processPayment(OrderCreatedEvent event) {

        String key = "payment_service:" + event.getOrderId();
        if (idempotencyService.isProcessed(key)) {
            System.out.println("Payment already processed for order: " + event.getOrderId());
            return;
        }

        System.out.println("Processing payment for order: " + event.getOrderId());

        PaymentSuccessEvent paymentSuccessEvent = new PaymentSuccessEvent();
        paymentSuccessEvent.setOrderId(event.getOrderId());
        paymentSuccessEvent.setPaymentId(UUID.randomUUID().toString());
        paymentSuccessEvent.setStatus("SUCCESS");
        paymentSuccessEvent.setUserEmail(event.getUserEmail());

        idempotencyService.markProcessed(key);

        paymentProducer.sendMessage(paymentSuccessEvent);

    }
}


package com.oms.payment.service;

import com.oms.common.lib.events.OrderCreatedEvent;
import com.oms.common.lib.events.PaymentSuccessEvent;
import com.oms.payment.kafka.PaymentProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentProducer paymentProducer;

    public void processPayment(OrderCreatedEvent event) {
        System.out.println("Processing payment for order: " + event.getOrderId());

        PaymentSuccessEvent paymentSuccessEvent = new PaymentSuccessEvent();
        paymentSuccessEvent.setOrderId(event.getOrderId());
        paymentSuccessEvent.setPaymentId(UUID.randomUUID().toString());
        paymentSuccessEvent.setStatus("SUCCESS");
        paymentSuccessEvent.setUserEmail(event.getUserEmail());

        paymentProducer.sendMessage(paymentSuccessEvent);

    }
}


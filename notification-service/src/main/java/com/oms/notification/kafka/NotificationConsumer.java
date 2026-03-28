package com.oms.notification.kafka;

import com.oms.common.lib.events.PaymentSuccessEvent;
import com.oms.notification.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "inventory-topic", groupId = "notification-group")
    public void consume(PaymentSuccessEvent event) {
        System.out.println("Notification Consumer received event for order: " + event.getOrderId() + " to email: " + event.getUserEmail());

        String subject = "Order Confirmed: " + event.getOrderId();
        String body = "Dear Customer,\n\nYour order " + event.getOrderId() + " has been successfully processed and verified by our inventory. Thank you for shopping with us!\n\nStatus: " + event.getStatus();

        emailService.sendSimpleEmail(event.getUserEmail(), subject, body);
    }
}

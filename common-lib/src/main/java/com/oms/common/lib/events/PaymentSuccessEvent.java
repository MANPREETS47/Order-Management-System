package com.oms.common.lib.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentSuccessEvent extends BaseEvent {
    private String orderId;
    private String paymentId;
    private String status;
    private String userEmail;
}


package com.oms.common.lib.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreatedEvent extends BaseEvent {
    private String orderId;
    private String userId;
    private String userEmail;
}

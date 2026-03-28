package com.oms.common.lib.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreatedEvent extends BaseEvent {
    private String orderId;
    private String userId;

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
}

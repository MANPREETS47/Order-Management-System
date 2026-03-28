package com.oms.common.lib.events;

import com.oms.common.lib.enums.EventType;

public abstract class BaseEvent {
    private String eventId;
    private EventType eventType;
    private String timestamp;

    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }
    public EventType getEventType() { return eventType; }
    public void setEventType(EventType eventType) { this.eventType = eventType; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}

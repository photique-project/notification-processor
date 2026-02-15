package com.benchpress200.notificationprocessor.singlework.handler;

import com.benchpress200.notificationprocessor.singlework.consumer.payload.SingleWorkEventPayload;

public interface SingleWorkEventHandler {
    String getEventType();
    void handle(Long eventId, SingleWorkEventPayload payload);
}

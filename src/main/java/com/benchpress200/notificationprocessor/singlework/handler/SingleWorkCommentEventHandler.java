package com.benchpress200.notificationprocessor.singlework.handler;

import com.benchpress200.notificationprocessor.singlework.consumer.payload.SingleWorkCommentEventPayload;

public interface SingleWorkCommentEventHandler {
    String getEventType();
    void handle(Long eventId, SingleWorkCommentEventPayload payload);
}

package com.benchpress200.notificationprocessor.follow.handler;

import com.benchpress200.notificationprocessor.follow.consumer.payload.FollowEventPayload;

public interface FollowEventHandler {
    String getEventType();
    void handle(Long eventId, FollowEventPayload payload);
}

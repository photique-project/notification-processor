package com.benchpress200.notificationprocessor.exhibition.handler;

import com.benchpress200.notificationprocessor.exhibition.consumer.payload.ExhibitionEventPayload;

public interface ExhibitionEventHandler {
    String getEventType();
    void handle(Long eventId, ExhibitionEventPayload payload);
}

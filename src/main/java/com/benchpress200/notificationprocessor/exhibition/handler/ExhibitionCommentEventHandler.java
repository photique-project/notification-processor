package com.benchpress200.notificationprocessor.exhibition.handler;

import com.benchpress200.notificationprocessor.exhibition.consumer.payload.ExhibitionCommentEventPayload;

public interface ExhibitionCommentEventHandler {
    String getEventType();
    void handle(Long eventId, ExhibitionCommentEventPayload payload);
}

package com.benchpress200.notificationprocessor.common.exception;

public class OutboxPayloadDeserializationException extends NonRetryableEventException {
    public OutboxPayloadDeserializationException() {
        super("Failed to deserialize outbox event payload");
    }
}

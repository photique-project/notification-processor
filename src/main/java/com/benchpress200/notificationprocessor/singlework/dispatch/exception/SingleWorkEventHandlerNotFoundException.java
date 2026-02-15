package com.benchpress200.notificationprocessor.singlework.dispatch.exception;

import com.benchpress200.notificationprocessor.common.exception.NonRetryableEventException;

public class SingleWorkEventHandlerNotFoundException extends NonRetryableEventException {
    public SingleWorkEventHandlerNotFoundException(String eventType) {
        super(String.format("Singlework handler [%s] not found", eventType));
    }
}

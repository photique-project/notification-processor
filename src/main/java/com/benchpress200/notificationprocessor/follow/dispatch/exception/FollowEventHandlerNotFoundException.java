package com.benchpress200.notificationprocessor.follow.dispatch.exception;

import com.benchpress200.notificationprocessor.common.exception.NonRetryableEventException;

public class FollowEventHandlerNotFoundException  extends NonRetryableEventException {
    public FollowEventHandlerNotFoundException(String eventType) {
        super(String.format("Follow handler [%s] not found", eventType));
    }
}

package com.benchpress200.notificationprocessor.exhibition.dispatch.exception;

import com.benchpress200.notificationprocessor.common.exception.NonRetryableEventException;

public class ExhibitionEventHandlerNotFoundException extends NonRetryableEventException {
    public ExhibitionEventHandlerNotFoundException(String eventType) {
      super(String.format("Exhibition handler [%s] not found", eventType));
    }
}

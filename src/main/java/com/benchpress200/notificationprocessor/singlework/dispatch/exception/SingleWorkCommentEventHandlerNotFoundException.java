package com.benchpress200.notificationprocessor.singlework.dispatch.exception;

import com.benchpress200.notificationprocessor.common.exception.NonRetryableEventException;

public class SingleWorkCommentEventHandlerNotFoundException extends NonRetryableEventException {
  public SingleWorkCommentEventHandlerNotFoundException(String eventType) {
    super(String.format("Singlework comment handler [%s] not found", eventType));
  }
}

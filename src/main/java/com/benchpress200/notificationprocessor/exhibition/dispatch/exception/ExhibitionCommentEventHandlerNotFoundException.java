package com.benchpress200.notificationprocessor.exhibition.dispatch.exception;

import com.benchpress200.notificationprocessor.common.exception.NonRetryableEventException;

public class ExhibitionCommentEventHandlerNotFoundException extends NonRetryableEventException {
  public ExhibitionCommentEventHandlerNotFoundException(String eventType) {
    super(String.format("Exhibition comment handler [%s] not found", eventType));
  }
}

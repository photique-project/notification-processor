package com.benchpress200.notificationprocessor.singlework.dispatch;

import com.benchpress200.notificationprocessor.singlework.consumer.payload.SingleWorkCommentEventPayload;
import com.benchpress200.notificationprocessor.singlework.dispatch.exception.SingleWorkCommentEventHandlerNotFoundException;
import com.benchpress200.notificationprocessor.singlework.handler.SingleWorkCommentEventHandler;
import com.benchpress200.notificationprocessor.singlework.handler.SingleWorkEventHandler;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class SingleWorkCommentEventDispatcher {
    private final Map<String, SingleWorkCommentEventHandler> handlers;

    public SingleWorkCommentEventDispatcher(List<SingleWorkCommentEventHandler> handlers) {
        this.handlers = handlers.stream()
                .collect(java.util.stream.Collectors.toUnmodifiableMap(
                        SingleWorkCommentEventHandler::getEventType,
                        h -> h
                ));
    }

    public void dispatch(
            String eventType,
            Long eventId,
            SingleWorkCommentEventPayload payload
    ) {
        SingleWorkCommentEventHandler handler = handlers.get(eventType);

        if (handler == null) {
            throw new SingleWorkCommentEventHandlerNotFoundException(eventType);
        }

        handler.handle(eventId, payload);
    }

    public boolean isSupported(String eventType) {
        SingleWorkCommentEventHandler handler = handlers.get(eventType);

        return handler != null;
    }
}

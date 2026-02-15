package com.benchpress200.notificationprocessor.follow.dispatch;

import com.benchpress200.notificationprocessor.follow.consumer.payload.FollowEventPayload;
import com.benchpress200.notificationprocessor.follow.dispatch.exception.FollowEventHandlerNotFoundException;
import com.benchpress200.notificationprocessor.follow.handler.FollowEventHandler;
import com.benchpress200.notificationprocessor.singlework.consumer.payload.SingleWorkEventPayload;
import com.benchpress200.notificationprocessor.singlework.dispatch.exception.SingleWorkEventHandlerNotFoundException;
import com.benchpress200.notificationprocessor.singlework.handler.SingleWorkEventHandler;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class FollowEventDispatcher {
    private final Map<String, FollowEventHandler> handlers;

    public FollowEventDispatcher(List<FollowEventHandler> handlers) {
        this.handlers = handlers.stream()
                .collect(java.util.stream.Collectors.toUnmodifiableMap(
                        FollowEventHandler::getEventType,
                        h -> h
                ));
    }

    public void dispatch(
            String eventType,
            Long eventId,
            FollowEventPayload payload
    ) {
        FollowEventHandler handler = handlers.get(eventType);

        if (handler == null) {
            throw new FollowEventHandlerNotFoundException(eventType);
        }

        handler.handle(eventId, payload);
    }

    public boolean isSupported(String eventType) {
        FollowEventHandler handler = handlers.get(eventType);

        return handler != null;
    }
}

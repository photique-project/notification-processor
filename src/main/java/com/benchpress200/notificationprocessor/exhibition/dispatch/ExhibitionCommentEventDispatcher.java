package com.benchpress200.notificationprocessor.exhibition.dispatch;

import com.benchpress200.notificationprocessor.exhibition.consumer.payload.ExhibitionCommentEventPayload;
import com.benchpress200.notificationprocessor.exhibition.dispatch.exception.ExhibitionCommentEventHandlerNotFoundException;
import com.benchpress200.notificationprocessor.exhibition.handler.ExhibitionCommentEventHandler;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ExhibitionCommentEventDispatcher {
    private final Map<String, ExhibitionCommentEventHandler> handlers;

    public ExhibitionCommentEventDispatcher(List<ExhibitionCommentEventHandler> handlers) {
        this.handlers = handlers.stream()
                .collect(java.util.stream.Collectors.toUnmodifiableMap(
                        ExhibitionCommentEventHandler::getEventType,
                        h -> h
                ));
    }

    public void dispatch(
            String eventType,
            Long eventId,
            ExhibitionCommentEventPayload payload
    ) {
        ExhibitionCommentEventHandler handler = handlers.get(eventType);

        if (handler == null) {
            throw new ExhibitionCommentEventHandlerNotFoundException(eventType);
        }

        handler.handle(eventId, payload);
    }
}

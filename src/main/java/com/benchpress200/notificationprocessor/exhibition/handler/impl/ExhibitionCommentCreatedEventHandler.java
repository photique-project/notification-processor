package com.benchpress200.notificationprocessor.exhibition.handler.impl;

import com.benchpress200.notificationprocessor.common.constant.EventType;
import com.benchpress200.notificationprocessor.exhibition.consumer.payload.ExhibitionCommentEventPayload;
import com.benchpress200.notificationprocessor.exhibition.handler.ExhibitionCommentEventHandler;
import com.benchpress200.notificationprocessor.notification.enumeration.NotificationType;
import com.benchpress200.notificationprocessor.notification.record.NotificationRecord;
import com.benchpress200.notificationprocessor.notification.repository.NotificationRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExhibitionCommentCreatedEventHandler implements ExhibitionCommentEventHandler {
    private final NotificationRepository notificationRepository;

    @Override
    public String getEventType() {
        return EventType.CREATED;
    }

    @Override
    public void handle(
            Long eventId,
            ExhibitionCommentEventPayload payload
    ) {
        Long receiverId = payload.getExhibitionWriterId();
        Long exhibitionId = payload.getExhibitionId();
        LocalDateTime now = LocalDateTime.now();

        NotificationRecord record = NotificationRecord.of(
                receiverId,
                NotificationType.EXHIBITION_COMMENT_CREATED,
                exhibitionId,
                eventId,
                now
        );

        notificationRepository.insertNotification(record);
    }
}

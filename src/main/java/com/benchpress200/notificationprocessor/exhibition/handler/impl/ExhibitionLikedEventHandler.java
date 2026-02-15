package com.benchpress200.notificationprocessor.exhibition.handler.impl;

import com.benchpress200.notificationprocessor.common.constant.EventType;
import com.benchpress200.notificationprocessor.exhibition.consumer.payload.ExhibitionEventPayload;
import com.benchpress200.notificationprocessor.exhibition.handler.ExhibitionEventHandler;
import com.benchpress200.notificationprocessor.notification.enumeration.NotificationType;
import com.benchpress200.notificationprocessor.notification.record.NotificationRecord;
import com.benchpress200.notificationprocessor.notification.repository.NotificationRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExhibitionLikedEventHandler implements ExhibitionEventHandler {
    private final NotificationRepository notificationRepository;

    @Override
    public String getEventType() {
        return EventType.LIKED;
    }

    @Override
    public void handle(
            Long eventId,
            ExhibitionEventPayload payload
    ) {
        Long writerId = payload.getWriter().getId();
        Long exhibitionId = payload.getId();
        LocalDateTime now = LocalDateTime.now();

        NotificationRecord record = NotificationRecord.of(
                writerId,
                NotificationType.EXHIBITION_LIKED,
                exhibitionId,
                eventId,
                now
        );

        notificationRepository.insertNotification(record);
    }
}

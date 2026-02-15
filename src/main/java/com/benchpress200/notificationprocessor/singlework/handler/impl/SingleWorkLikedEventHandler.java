package com.benchpress200.notificationprocessor.singlework.handler.impl;

import com.benchpress200.notificationprocessor.common.constant.EventType;
import com.benchpress200.notificationprocessor.notification.enumeration.NotificationType;
import com.benchpress200.notificationprocessor.notification.record.NotificationRecord;
import com.benchpress200.notificationprocessor.notification.repository.NotificationRepository;
import com.benchpress200.notificationprocessor.singlework.consumer.payload.SingleWorkEventPayload;
import com.benchpress200.notificationprocessor.singlework.handler.SingleWorkEventHandler;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SingleWorkLikedEventHandler implements SingleWorkEventHandler {
    private final NotificationRepository notificationRepository;

    @Override
    public String getEventType() {
        return EventType.LIKED;
    }

    @Override
    public void handle(
            Long eventId,
            SingleWorkEventPayload payload
    ) {
        Long writerId = payload.getWriter().getId();
        Long singleWorkId = payload.getId();
        LocalDateTime now = LocalDateTime.now();

        NotificationRecord record = NotificationRecord.of(
                writerId,
                NotificationType.SINGLEWORK_LIKED,
                singleWorkId,
                eventId,
                now
        );

        notificationRepository.insertNotification(record);
    }
}

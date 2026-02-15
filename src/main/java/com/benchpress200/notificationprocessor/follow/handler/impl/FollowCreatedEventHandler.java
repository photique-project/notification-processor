package com.benchpress200.notificationprocessor.follow.handler.impl;

import com.benchpress200.notificationprocessor.common.constant.EventType;
import com.benchpress200.notificationprocessor.follow.consumer.payload.FollowEventPayload;
import com.benchpress200.notificationprocessor.follow.handler.FollowEventHandler;
import com.benchpress200.notificationprocessor.notification.enumeration.NotificationType;
import com.benchpress200.notificationprocessor.notification.record.NotificationRecord;
import com.benchpress200.notificationprocessor.notification.repository.NotificationRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FollowCreatedEventHandler implements FollowEventHandler {
    private final NotificationRepository notificationRepository;

    @Override
    public String getEventType() {
        return EventType.CREATED;
    }

    @Override
    public void handle(
            Long eventId,
            FollowEventPayload payload
    ) {
        Long followerId = payload.getFollowerId();
        Long followeeId = payload.getFolloweeId();
        LocalDateTime now = LocalDateTime.now();

        NotificationRecord record = NotificationRecord.of(
                followeeId,
                NotificationType.FOLLOW,
                followerId,
                eventId,
                now
        );

        notificationRepository.insertNotification(record);
    }
}

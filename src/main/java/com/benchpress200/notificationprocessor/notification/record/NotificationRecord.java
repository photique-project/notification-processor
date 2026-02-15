package com.benchpress200.notificationprocessor.notification.record;

import com.benchpress200.notificationprocessor.notification.enumeration.NotificationType;
import java.time.LocalDateTime;

public record NotificationRecord(
        Long receiverId,
        String type,
        Long targetId,
        boolean isRead,
        Long eventId,
        LocalDateTime createdAt
) {
    public static NotificationRecord of(
            Long receiverId,
            NotificationType type,
            Long targetId,
            Long eventId,
            LocalDateTime createdAt
    ) {
        return new NotificationRecord(
            receiverId,
                type.getValue(),
                targetId,
                false,
                eventId,
                createdAt
        );
    }
}

package com.benchpress200.notificationprocessor.exhibition.handler.impl;

import com.benchpress200.notificationprocessor.common.constant.EventType;
import com.benchpress200.notificationprocessor.exhibition.consumer.payload.ExhibitionEventPayload;
import com.benchpress200.notificationprocessor.exhibition.handler.ExhibitionEventHandler;
import com.benchpress200.notificationprocessor.notification.enumeration.NotificationType;
import com.benchpress200.notificationprocessor.notification.record.NotificationRecord;
import com.benchpress200.notificationprocessor.notification.repository.NotificationRepository;
import com.benchpress200.notificationprocessor.user.repository.FollowRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExhibitionCreatedEventHandler implements ExhibitionEventHandler {
    @Value("${spring.notification.batch-size}")
    private int PAGE_SIZE;

    private final FollowRepository followRepository;
    private final NotificationRepository notificationRepository;

    @Override
    public String getEventType() {
        return EventType.CREATED;
    }

    @Override
    public void handle(
            Long eventId,
            ExhibitionEventPayload payload
    ) {
        Long writerId = payload.getWriter().getId();
        Long exhibitionId = payload.getId();
        long cursor = 0L;

        while (true) {
            List<Long> followerIds = followRepository.findFollowerIdsByFolloweeIdAfter(
                    writerId,
                    cursor,
                    PAGE_SIZE
            );

            if (followerIds.isEmpty()) {
                break;
            }

            LocalDateTime now = LocalDateTime.now();

            List<NotificationRecord> records = followerIds.stream()
                    .map(followerId -> NotificationRecord.of(
                            followerId,
                            NotificationType.EXHIBITION_CREATED,
                            exhibitionId,
                            eventId,
                            now
                    ))
                    .toList();

            notificationRepository.insertNotifications(records, PAGE_SIZE);

            cursor = followerIds.get(followerIds.size() - 1);
        }
    }
}

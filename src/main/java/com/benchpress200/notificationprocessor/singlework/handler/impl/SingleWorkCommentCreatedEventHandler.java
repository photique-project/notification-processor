package com.benchpress200.notificationprocessor.singlework.handler.impl;

import com.benchpress200.notificationprocessor.common.constant.EventType;
import com.benchpress200.notificationprocessor.notification.enumeration.NotificationType;
import com.benchpress200.notificationprocessor.notification.record.NotificationRecord;
import com.benchpress200.notificationprocessor.notification.repository.NotificationRepository;
import com.benchpress200.notificationprocessor.singlework.consumer.payload.SingleWorkCommentEventPayload;
import com.benchpress200.notificationprocessor.singlework.handler.SingleWorkCommentEventHandler;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SingleWorkCommentCreatedEventHandler implements SingleWorkCommentEventHandler {
    private final NotificationRepository notificationRepository;

    @Override
    public String getEventType() {
        return EventType.CREATED;
    }

    @Override
    public void handle(
            Long eventId,
            SingleWorkCommentEventPayload payload
    ) {
        // 단일작품 정보가 뎁스에 들어가도록 다시 이벤트 발행 수정
        Long receiverId = payload.getSingleWorkWriterId();
        Long singleWorkId = payload.getSingleWorkId();
        LocalDateTime now = LocalDateTime.now();

        NotificationRecord record = NotificationRecord.of(
                receiverId,
                NotificationType.SINGLEWORK_COMMENT_CREATED,
                singleWorkId,
                eventId,
                now
        );

        notificationRepository.insertNotification(record);
    }
}

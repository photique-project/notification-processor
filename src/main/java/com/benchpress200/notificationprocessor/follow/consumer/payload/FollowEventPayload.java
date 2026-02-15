package com.benchpress200.notificationprocessor.follow.consumer.payload;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class FollowEventPayload {
    private Long followerId;
    private Long followeeId;
    private LocalDateTime createdAt;
}

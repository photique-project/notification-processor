package com.benchpress200.notificationprocessor.singlework.consumer.payload;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class SingleWorkCommentEventPayload {
    private Long id;
    private Writer writer;
    private Long singleWorkId;
    private Long singleWorkWriterId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Getter
    public static class Writer {
        private Long id;
        private String nickname;
        private String profileImage;
    }
}

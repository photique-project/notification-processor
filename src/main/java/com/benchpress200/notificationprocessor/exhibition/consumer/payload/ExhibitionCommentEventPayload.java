package com.benchpress200.notificationprocessor.exhibition.consumer.payload;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ExhibitionCommentEventPayload {
    private Long id;
    private Writer writer;
    private Long exhibitionId;
    private Long exhibitionWriterId;
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

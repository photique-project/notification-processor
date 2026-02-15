package com.benchpress200.notificationprocessor.notification.repository;

import com.benchpress200.notificationprocessor.notification.record.NotificationRecord;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NotificationRepository {
    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void insertNotifications(
            List<NotificationRecord> records,
            int batchSize
    ) {
        jdbcTemplate.batchUpdate(
                """
                INSERT IGNORE INTO notifications
                  (receiver_id, type, target_id, is_read, event_id, created_at)
                VALUES (?, ?, ?, ?, ?, ?)
                """,
                records,
                batchSize,
                (ps, row) -> {
                    ps.setLong(1, row.receiverId());
                    ps.setString(2, row.type());
                    ps.setLong(3, row.targetId());
                    ps.setBoolean(4, row.isRead());
                    ps.setLong(5, row.eventId());
                    ps.setObject(6, row.createdAt());
                }
        );
    }
}

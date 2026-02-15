package com.benchpress200.notificationprocessor.user.repository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FollowRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Long> findFollowerIdsByFolloweeIdAfter(
            long followeeId,
            long cursorFollowerId,
            int limit
    ) {
        return jdbcTemplate.queryForList(
                """
                SELECT follower_id
                FROM follows
                WHERE followee_id = ? AND follower_id > ?
                ORDER BY follower_id ASC
                LIMIT ?
                """,
                Long.class,
                followeeId,
                cursorFollowerId,
                limit
        );
    }
}

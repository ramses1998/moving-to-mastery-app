package com.acme.movingtomastery.repository;

import com.acme.movingtomastery.entity.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class NotificationRepository {

    private final JdbcTemplate jdbcTemplate;

    public Collection<Notification> findAll() {
        return jdbcTemplate.query("""
                        SELECT * FROM notification
                        """,
                new RowMapper<Notification>() {
                    @Override
                    public Notification mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return Notification.builder()
                                .id(UUID.fromString(rs.getString("id")))
                                .title(rs.getString("title"))
                                .content(rs.getString("content"))
                                .issuerId(rs.getString("issuer_id"))
                                .creationDate(rs.getString("creation_date"))
                                .build();
                    }
                }
        );
    }

    public Notification findById(final String id) {
        return jdbcTemplate.query("""
                        SELECT * FROM notification
                        WHERE id=?
                        """,
                ps -> {
                    ps.setString(1, id);
                },
                new ResultSetExtractor<Notification>() {
                    @Override
                    public Notification extractData(ResultSet rs) throws SQLException {
                        // If RecordSet is empty(No Data found)
                        if (!rs.next()) {
                            return null;
                        }
                        return Notification.builder()
                                .id(UUID.fromString(rs.getString("id")))
                                .title(rs.getString("title"))
                                .content(rs.getString("content"))
                                .issuerId(rs.getString("issuer_id"))
                                .creationDate(rs.getString("creation_date"))
                                .build();
                    }
                }
        );
    }

    public Notification create(final Notification notification) {
        jdbcTemplate.update("""
                INSERT INTO notification(
                id,
                title,
                content,
                issuer_id,
                creation_date
                )
                VALUES(?,?,?,?,?)
                """,
                ps -> {
                    ps.setString(1, notification.getId().toString());
                    ps.setString(2, notification.getTitle());
                    ps.setString(3, notification.getContent());
                    ps.setString(4, notification.getIssuerId());
                    ps.setString(5, notification.getCreationDate());
                }
        );

        return findById(notification.getId().toString());
    }

    public Notification update(final Notification notification) {
        jdbcTemplate.update("""
                UPDATE notification SET               
                title=?,
                content=?,
                issuer_id=?,
                creation_date=?
                WHERE id=?                                
                """,
                ps -> {
                    ps.setString(1, notification.getTitle());
                    ps.setString(2, notification.getContent());
                    ps.setString(3, notification.getIssuerId());
                    ps.setString(4, notification.getCreationDate());
                    ps.setString(5, notification.getId().toString());
                }
        );

        return findById(notification.getId().toString());
    }

    public Notification delete(final String id) {
        var notificationFromDatabase = findById(id);
        jdbcTemplate.update(
                "DELETE * FROM notification WHERE id=?",
                ps -> {
                    ps.setString(1, id);
                }
        );

        if (!Objects.equals(findById(id), notificationFromDatabase)) return notificationFromDatabase;
        return null;
    }
}

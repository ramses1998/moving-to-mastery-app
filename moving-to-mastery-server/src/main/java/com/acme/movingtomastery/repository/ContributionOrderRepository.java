package com.acme.movingtomastery.repository;

import com.acme.movingtomastery.entity.ContributionOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ContributionOrderRepository {

    private final JdbcTemplate jdbcTemplate;

    public Collection<ContributionOrder> findAll() {
        return jdbcTemplate.query("""
                SELECT * FROM contribution_order
                """,
                new RowMapper<ContributionOrder>() {
                    @Override
                    public ContributionOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return ContributionOrder.builder()
                                .id(UUID.fromString(rs.getString("id")))
                                .order(rs.getString("order"))
                                .memberId(rs.getString("member_id"))
                                .build();
                    }
                }
        );
    }

    public ContributionOrder findById(final String id) {
        return jdbcTemplate.query("""
                SELECT * FROM contribution_order
                WHERE id=?
                """,
                ps -> {
                    ps.setString(1, id);
                },
                new ResultSetExtractor<ContributionOrder>() {
                    @Override
                    public ContributionOrder extractData(ResultSet rs) throws SQLException {
                        // If RecordSet is empty(No Data found)
                        if (!rs.next()) {
                            return null;
                        }
                        return ContributionOrder.builder()
                                .id(UUID.fromString(rs.getString("id")))
                                .order(rs.getString("order"))
                                .memberId(rs.getString("member_id"))
                                .build();
                    }
                }
        );

    }

    public ContributionOrder findByMemberId(final String id) {
        return jdbcTemplate.query("""
                SELECT * FROM contribution_order
                WHERE member_id=?
                """,
                ps -> {
                    ps.setString(1, id);
                },
                new ResultSetExtractor<ContributionOrder>() {
                    @Override
                    public ContributionOrder extractData(ResultSet rs) throws SQLException {
                        // If RecordSet is empty(No Data found)
                        if (!rs.next()) {
                            return null;
                        }
                        return ContributionOrder.builder()
                                .id(UUID.fromString(rs.getString("id")))
                                .order(rs.getString("order"))
                                .memberId(rs.getString("member_id"))
                                .build();
                    }
                }
        );

    }


    public ContributionOrder create(final ContributionOrder contributionOrder) {
        jdbcTemplate.update("""
                INSERT INTO contribution_order(id, order, member_id)
                VALUES(?,?,?)
                """,
                ps -> {
                    ps.setString(1, contributionOrder.getId().toString());
                    ps.setString(2, contributionOrder.getOrder());
                    ps.setString(3, contributionOrder.getMemberId());

                }
        );
        return findById(contributionOrder.getId().toString());
    }

    public ContributionOrder update(final ContributionOrder contributionOrder) {
        jdbcTemplate.update("""
                UPDATE contribution_order SET
                order=?,
                member_id=?
                WHERE id=?
                """,
                ps -> {
                    ps.setString(1, contributionOrder.getOrder());
                    ps.setString(2, contributionOrder.getMemberId());
                    ps.setString(3, contributionOrder.getId().toString());
                }
        );
        return findById(contributionOrder.getId().toString());
    }
}

package com.acme.movingtomastery.repository;

import com.acme.movingtomastery.entity.Fine;
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
public class FineRepository {

    private final JdbcTemplate jdbcTemplate;

    public Collection<Fine> findAll() {
        return jdbcTemplate.query("""
                        SELECT * FROM fine
                        """,
                new RowMapper<Fine>() {
                    @Override
                    public Fine mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return Fine.builder()
                                .id(UUID.fromString(rs.getString("id")))
                                .memberId(rs.getString("member_id"))
                                .amount(rs.getInt("amount"))
                                .currency(rs.getString("currency"))
                                .creationDate(rs.getString("creation_date"))
                                .build();
                    }
                }
        );
    }

    public Fine findById(final String id) {
        return jdbcTemplate.query("""
                        SELECT * FROM fine
                        WHERE id=?
                        """,
                ps -> {
                    ps.setString(1, id);
                },
                new ResultSetExtractor<Fine>() {
                    @Override
                    public Fine extractData(ResultSet rs) throws SQLException {
                        // If RecordSet is empty(No Data found)
                        if (!rs.next()) {
                            return null;
                        }
                        return Fine.builder()
                                .id(UUID.fromString(rs.getString("id")))
                                .memberId(rs.getString("member_id"))
                                .amount(rs.getInt("amount"))
                                .currency(rs.getString("currency"))
                                .creationDate(rs.getString("creation_date"))
                                .build();
                    }
                }
        );
    }

    public Fine create(final Fine fine) {
        jdbcTemplate.update("""
                INSERT INTO fine(id, member_id, amount, currency, creation_date)
                VALUES(?,?,?,?,?)
                """,
                ps -> {
                    ps.setString(1, fine.getId().toString());
                    ps.setString(2, fine.getMemberId());
                    ps.setInt(3, fine.getAmount());
                    ps.setString(4, fine.getCurrency());
                    ps.setString(5, fine.getCreationDate());
                }
        );

        return findById(fine.getId().toString());
    }

    public Fine update(final Fine fine) {
        jdbcTemplate.update("""
                UPDATE fine SET
                member_id=?,
                amount=?,
                currency=?,
                creation_date=?
                WHERE id=?
                """,
                ps -> {
                    ps.setString(1, fine.getMemberId());
                    ps.setInt(2, fine.getAmount());
                    ps.setString(3, fine.getCurrency());
                    ps.setString(4, fine.getCreationDate());
                    ps.setString(5, fine.getId().toString());
                }
        );

        return findById(fine.getId().toString());
    }

    public Fine delete(final String id) {
        var fineToDelete = findById(id);
        jdbcTemplate.update(
                """
                DELETE FROM fine
                WHERE id=?
                """,
                ps -> {
                    ps.setString(1, id);
                }
        );
        if (!Objects.equals(findById(id), fineToDelete)) return fineToDelete;
        return null;
    }
}

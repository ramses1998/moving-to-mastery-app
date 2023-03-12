package com.acme.movingtomastery.repository;

import com.acme.movingtomastery.entity.Transaction;
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
public class TransactionRepository {

    private final JdbcTemplate jdbcTemplate;

    public Collection<Transaction> findAll() {
        return jdbcTemplate.query("""
                        SELECT * FROM transaction
                        """,
                new RowMapper<Transaction>() {
                    @Override
                    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return Transaction.builder()
                                .id(UUID.fromString(rs.getString("id")))
                                .amount(rs.getInt("amount"))
                                .senderId(rs.getString("sender_id"))
                                .receiverId(rs.getString("receiver_id"))
                                .currency(rs.getString("currency"))
                                .type(rs.getString("type"))
                                .creationDate(rs.getString("creation_date"))
                                .build();
                    }
                }
        );
    }

    public Collection<Transaction> findByType(final String type) {
        return jdbcTemplate.query("""
                        SELECT * FROM transaction
                        WHERE type=?
                        """,
                ps -> {
                    ps.setString(1, type);

                },
                new RowMapper<Transaction>() {
                    @Override
                    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return Transaction.builder()
                                .id(UUID.fromString(rs.getString("id")))
                                .amount(rs.getInt("amount"))
                                .senderId(rs.getString("sender_id"))
                                .receiverId(rs.getString("receiver_id"))
                                .currency(rs.getString("currency"))
                                .type(rs.getString("type"))
                                .creationDate(rs.getString("creation_date"))
                                .build();
                    }
                }
        );
    }

    public Transaction findById(final String id) {
        return jdbcTemplate.query("""
                        SELECT * FROM transaction
                        WHERE id=?
                        """,
                ps -> {
                    ps.setString(1, id);
                },
                new ResultSetExtractor<Transaction>() {
                    @Override
                    public Transaction extractData(ResultSet rs) throws SQLException {
                        // If RecordSet is empty(No Data found)
                        if (!rs.next()) {
                            return null;
                        }
                        return Transaction.builder()
                                .id(UUID.fromString(rs.getString("id")))
                                .amount(rs.getInt("amount"))
                                .senderId(rs.getString("sender_id"))
                                .receiverId(rs.getString("receiver_id"))
                                .currency(rs.getString("currency"))
                                .type(rs.getString("type"))
                                .creationDate(rs.getString("creation_date"))
                                .build();
                    }
                }
        );
    }

    public Transaction create(final Transaction transaction) {
        jdbcTemplate.update("""
                INSERT INTO transaction(
                id,              
                amount,
                sender_id,
                receiver_id,
                currency,
                type,
                creation_date
                )               
                VALUES(?,?,?,?,?,?,?)
                """,
                ps -> {
                    ps.setString(1, transaction.getId().toString());
                    ps.setInt(2, transaction.getAmount());
                    ps.setString(3, transaction.getSenderId());
                    ps.setString(4, transaction.getReceiverId());
                    ps.setString(5, transaction.getCurrency());
                    ps.setString(6, transaction.getType());
                    ps.setString(7, transaction.getCreationDate());
                }
        );

        return findById(transaction.getId().toString());
    }

    public Transaction delete(final String id) {
        var transactionFromDatabase = findById(id);
        jdbcTemplate.update(
                "DELETE * FROM transaction WHERE id=?",
                ps -> {
                    ps.setString(1, id);
                }
        );

        if (!Objects.equals(findById(id), transactionFromDatabase)) return transactionFromDatabase;
        return null;
    }
}

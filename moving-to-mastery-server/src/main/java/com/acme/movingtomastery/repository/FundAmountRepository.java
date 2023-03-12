package com.acme.movingtomastery.repository;

import com.acme.movingtomastery.entity.ContributionAmount;
import com.acme.movingtomastery.entity.FundAmount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FundAmountRepository {

    private final JdbcTemplate jdbcTemplate;

    public FundAmount find() {
        return jdbcTemplate.query("""
                SELECT * FROM fund_repository_amount
                """,
                new ResultSetExtractor<FundAmount>() {
                    @Override
                    public FundAmount extractData(ResultSet rs) throws SQLException {
                        // If RecordSet is empty(No Data found)
                        if (!rs.next()) {
                            return null;
                        }
                        return FundAmount.builder()
                                .id(UUID.fromString(rs.getString("id")))
                                .amount(rs.getInt("amount"))
                                .currency(rs.getString("currency"))
                                .build();
                    }
                }
        );

    }

    public FundAmount update(final FundAmount fundAmount) {
        jdbcTemplate.update("""
                UPDATE fund_repository_amount SET
                amount=?,
                currency=?
                WHERE id=?
                """,
                ps -> {
                    ps.setInt(1, fundAmount.getAmount());
                    ps.setString(2, fundAmount.getCurrency());
                    ps.setString(3, fundAmount.getId().toString());
                }
        );

        var fundAmountFromDataBase = find();

        if (Objects.equals(fundAmountFromDataBase, null)) {
            log.info("fund_repository_amount could not be updated in the Database.");
            return null;
        }
        return fundAmountFromDataBase;
    }
}

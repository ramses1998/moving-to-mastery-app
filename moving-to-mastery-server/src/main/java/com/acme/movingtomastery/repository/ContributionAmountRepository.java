package com.acme.movingtomastery.repository;

import com.acme.movingtomastery.entity.ContributionAmount;
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
public class ContributionAmountRepository {

    private final JdbcTemplate jdbcTemplate;

    public ContributionAmount find() {
        return jdbcTemplate.query("""
                SELECT * FROM contribution_amount
                """,
                new ResultSetExtractor<ContributionAmount>() {
                    @Override
                    public ContributionAmount extractData(ResultSet rs) throws SQLException {
                        // If RecordSet is empty(No Data found)
                        if (!rs.next()) {
                            return null;
                        }
                        return ContributionAmount.builder()
                                .id(UUID.fromString(rs.getString("id")))
                                .amount(rs.getInt("amount"))
                                .currency(rs.getString("currency"))
                                .build();
                    }
                }
        );
    }

    public ContributionAmount update(final ContributionAmount contributionAmount) {
        jdbcTemplate.update("""
                UPDATE contribution_amount SET
                amount=?,
                currency=?
                WHERE id=?
                """,
                ps -> {
                    ps.setInt(1, contributionAmount.getAmount());
                    ps.setString(2, contributionAmount.getCurrency());
                    ps.setString(3, contributionAmount.getId().toString());
                }
        );

        var contributionAmountFromDataBase = find();

        if (Objects.equals(contributionAmountFromDataBase, null)) {
            log.info("Contribution-amount could not be updated in the Database.");
            return null;
        }
        return contributionAmountFromDataBase;
    }

}

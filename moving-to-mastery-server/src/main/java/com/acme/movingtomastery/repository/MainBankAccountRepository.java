package com.acme.movingtomastery.repository;

import com.acme.movingtomastery.entity.MainBankAccount;
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
public class MainBankAccountRepository {

    private final JdbcTemplate jdbcTemplate;

    public MainBankAccount find() {
        return jdbcTemplate.query("""
                SELECT * FROM main_bank_account
                """,
                new ResultSetExtractor<MainBankAccount>() {
                    @Override
                    public MainBankAccount extractData(ResultSet rs) throws SQLException {
                        // If RecordSet is empty(No Data found)
                        if (!rs.next()) {
                            return null;
                        }
                        return MainBankAccount.builder()
                                .id(UUID.fromString(rs.getString("id")))
                                .iban(rs.getString("iban"))
                                .paypal(rs.getString("paypal"))
                                .currency(rs.getString("currency"))
                                .build();
                    }
                }
        );

    }

    public MainBankAccount update(final MainBankAccount mainBankAccount) {
        jdbcTemplate.update("""
                UPDATE main_bank_account SET
                iban=?,
                paypal=?,
                currency=?
                WHERE id=?
                """,
                ps -> {
                    ps.setString(1, mainBankAccount.getIban());
                    ps.setString(2, mainBankAccount.getPaypal());
                    ps.setString(3, mainBankAccount.getCurrency());
                    ps.setString(4, mainBankAccount.getId().toString());
                }
        );

        var mainBankAccountFromDataBase = find();

        if (Objects.equals(mainBankAccountFromDataBase, null)) {
            log.info("main bank account could not be updated in the Database.");
            return null;
        }
        return mainBankAccountFromDataBase;
    }

}

package com.acme.movingtomastery.repository;

import com.acme.movingtomastery.entity.Member;
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
public class MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public Collection<Member> findAll() {
        return jdbcTemplate.query("""
                        SELECT * FROM member                      
                        """,
                new RowMapper<Member>() {
                    @Override
                    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return getMemberFromJDBCResultSet(rs);
                    }
                }
        );
    }

    public Collection<Member> findNonDeleted() {
        return jdbcTemplate.query("""
                        SELECT * FROM member
                        WHERE deleted=?
                        """,
                ps -> {
                    ps.setBoolean(1, false);
                },
                new RowMapper<Member>() {
                    @Override
                    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return getMemberFromJDBCResultSet(rs);
                    }
                }
        );
    }

    public Member findById(final String id) {
        return jdbcTemplate.query("""
                        SELECT * FROM member
                        WHERE id=?
                        """,
                ps -> {
                    ps.setString(1, id);
                },
                new ResultSetExtractor<Member>() {
                    @Override
                    public Member extractData(ResultSet rs) throws SQLException {
                        // If RecordSet is empty(No Data found)
                        if (!rs.next()) {
                            return null;
                        }
                        return getMemberFromJDBCResultSet(rs);
                    }
                }
        );
    }

    public Member findByPaypal(final String paypal) {
        return jdbcTemplate.query("""
                        SELECT * FROM member
                        WHERE paypal=?
                        """,
                ps -> {
                    ps.setString(1, paypal);
                },
                new ResultSetExtractor<Member>() {
                    @Override
                    public Member extractData(ResultSet rs) throws SQLException {
                        // If RecordSet is empty(No Data found)
                        if (!rs.next()) {
                            return null;
                        }
                        return getMemberFromJDBCResultSet(rs);
                    }
                }
        );

    }

    public Member create(final Member member) {
        jdbcTemplate.update("""
                INSERT INTO member(
                id,
                first_name,
                last_name,
                marital_status,
                country_of_residence,
                phone_number,
                address,
                paypal,
                profession,
                avatar,
                role           
                )
                VALUES(?,?,?,?,?,?,?,?,?,?,?)
                """,
                ps -> {
                    ps.setString(1, member.getId().toString());
                    ps.setString(2, member.getFirstName());
                    ps.setString(3, member.getLastName());
                    ps.setString(4, member.getMaritalStatus());
                    ps.setString(5, member.getCountryOfResidence());
                    ps.setString(6, member.getPhoneNumber());
                    ps.setString(7, member.getAddress());
                    ps.setString(8, member.getPaypal());
                    ps.setString(9, member.getProfession());
                    ps.setString(10, member.getAvatar());
                    ps.setString(11, member.getRole());
                }
        );

        return findById(member.getId().toString());
    }

    public Member update(final Member member) {
        jdbcTemplate.update("""
                UPDATE member SET              
                first_name=?,
                last_name=?,
                marital_status=?,
                country_of_residence=?,
                phone_number=?,
                address=?,
                paypal=?,
                profession=?,
                avatar=?,
                role=?           
                WHERE id=? AND deleted=?
                """,
                ps -> {
                    ps.setString(1, member.getFirstName());
                    ps.setString(2, member.getLastName());
                    ps.setString(3, member.getMaritalStatus());
                    ps.setString(4, member.getCountryOfResidence());
                    ps.setString(5, member.getPhoneNumber());
                    ps.setString(6, member.getAddress());
                    ps.setString(7, member.getPaypal());
                    ps.setString(8, member.getProfession());
                    ps.setString(9, member.getAvatar());
                    ps.setString(10, member.getRole());
                    ps.setString(11, member.getId().toString());
                    ps.setBoolean(12, false);
                }
        );

        return findById(member.getId().toString());
    }

    public Member delete(final String id) {
        jdbcTemplate.update(
                "UPDATE member SET deleted=? WHERE id=?",
                ps -> {
                    ps.setBoolean(1, true);
                    ps.setString(2, id);
                }
        );

        return findById(id);
    }

    private Member getMemberFromJDBCResultSet(ResultSet rs) throws SQLException {
        return Member.builder()
                .id(UUID.fromString(rs.getString("id")))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .maritalStatus(rs.getString("marital_status"))
                .countryOfResidence(rs.getString("country_of_residence"))
                .phoneNumber(rs.getString("phone_number"))
                .address(rs.getString("address"))
                .paypal(rs.getString("paypal"))
                .profession(rs.getString("profession"))
                .avatar(rs.getString("avatar"))
                .role(rs.getString("role"))
                .build();
    }
}

package com.sparta.memo.repository;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


// Memo 애플리케이션의 Memo 데이터베이스 처리를 담당하는 Repository 클래스입니다.



@Component // Spring 컨텍스트에서 이 클래스가 빈으로 관리되도록 선언합니다.
public class MemoRepository {

    private final JdbcTemplate jdbcTemplate; // JdbcTemplate 객체를 멤버 변수로 선언합니다.

    /**
     * MemoRepository의 생성자입니다.
     *
     * @param jdbcTemplate JdbcTemplate 객체, 데이터베이스 쿼리 실행에 사용됩니다.
     */
    public MemoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate; // JdbcTemplate 객체를 생성자에서 주입받아 멤버 변수에 할당합니다.
    }

    /**
     * Memo를 데이터베이스에 저장하는 메서드입니다.
     *
     * @param memo 저장할 Memo 객체
     * @return 저장된 Memo 객체
     */
    public Memo save(Memo memo) {
        // DB에 Memo 데이터를 저장합니다.
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 데이터베이스에서 생성된 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO memo (username, contents) VALUES (?, ?)";
        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, memo.getUsername());
                    preparedStatement.setString(2, memo.getContents());
                    return preparedStatement;
                },
                keyHolder);

        // 데이터베이스에서 받아온 생성된 기본 키를 Memo 객체에 할당합니다.
        Long id = keyHolder.getKey().longValue();
        memo.setId(id);

        return memo; // 저장된 Memo 객체를 반환합니다.
    }

    /**
     * 모든 Memo 목록을 조회하는 메서드입니다.
     *
     * @return MemoResponseDto 객체의 리스트
     */
    public List<MemoResponseDto> findAll() {
        // 데이터베이스에서 모든 Memo 데이터를 조회합니다.
        String sql = "SELECT * FROM memo";

        return jdbcTemplate.query(sql, new RowMapper<MemoResponseDto>() {
            @Override
            public MemoResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // ResultSet에서 조회한 Memo 데이터를 MemoResponseDto 객체로 변환합니다.
                Long id = rs.getLong("id");
                String username = rs.getString("username");
                String contents = rs.getString("contents");
                return new MemoResponseDto(id, username, contents);
            }
        });
    }

    /**
     * 특정 Memo를 업데이트하는 메서드입니다.
     *
     * @param id         업데이트할 Memo의 ID
     * @param requestDto 업데이트할 Memo의 데이터를 담은 MemoRequestDto 객체
     */
    public void update(Long id, MemoRequestDto requestDto) {
        // 데이터베이스에서 특정 Memo를 업데이트합니다.
        String sql = "UPDATE memo SET username = ?, contents = ? WHERE id = ?";
        jdbcTemplate.update(sql, requestDto.getUsername(), requestDto.getContents(), id);
    }

    /**
     * 특정 Memo를 삭제하는 메서드입니다.
     *
     * @param id 삭제할 Memo의 ID
     */
    public void delete(Long id) {
        // 데이터베이스에서 특정 Memo를 삭제합니다.
        String sql = "DELETE FROM memo WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    /**
     * 특정 ID를 가진 Memo를 조회하는 메서드입니다.
     *
     * @param id 조회할 Memo의 ID
     * @return ID에 해당하는 Memo 객체
     */
    public Memo findById(Long id) {
        // 데이터베이스에서 특정 ID를 가진 Memo를 조회합니다.
        String sql = "SELECT * FROM memo WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Memo memo = new Memo();
                memo.setUsername(resultSet.getString("username"));
                memo.setContents(resultSet.getString("contents"));
                return memo; // 조회된 Memo 객체를 반환합니다.
            } else {
                return null; // ID에 해당하는 Memo가 없으면 null을 반환합니다.
            }
        }, id);
    }
}
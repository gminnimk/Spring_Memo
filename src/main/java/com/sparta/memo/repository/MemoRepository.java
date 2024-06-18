package com.sparta.memo.repository;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import jakarta.persistence.EntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Component
public class MemoRepository {

    private final JdbcTemplate jdbcTemplate;

    public MemoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Memo 객체를 데이터베이스에 저장합니다.
     *
     * @param memo 저장할 Memo 객체
     * @return 저장된 Memo 객체
     */
    public Memo save(Memo memo) {
        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 데이터베이스에서 생성된 키를 보관하는 객체

        String sql = "INSERT INTO memo (username, contents) VALUES (?, ?)";
        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, memo.getUsername());
                    preparedStatement.setString(2, memo.getContents());
                    return preparedStatement;
                },
                keyHolder);

        // 저장 후 생성된 기본키를 Memo 객체에 할당합니다.
        Long id = keyHolder.getKey().longValue();
        memo.setId(id);

        return memo;
    }

    /**
     * 모든 Memo 데이터를 데이터베이스에서 조회합니다.
     *
     * @return MemoResponseDto 리스트
     */
    public List<MemoResponseDto> findAll() {
        // DB 조회
        String sql = "SELECT * FROM memo";

        return jdbcTemplate.query(sql, new RowMapper<MemoResponseDto>() {
            @Override
            public MemoResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // ResultSet에서 Memo 데이터를 추출하여 MemoResponseDto 객체로 변환합니다.
                Long id = rs.getLong("id");
                String username = rs.getString("username");
                String contents = rs.getString("contents");
                return new MemoResponseDto(id, username, contents);
            }
        });
    }

    /**
     * 주어진 ID에 해당하는 Memo 데이터를 데이터베이스에서 조회합니다.
     *
     * @param id 조회할 Memo의 ID
     * @return Memo 객체
     */
    public Memo findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM memo WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                // ResultSet에서 Memo 데이터를 추출하여 Memo 객체를 생성합니다.
                Memo memo = new Memo();
                memo.setUsername(resultSet.getString("username"));
                memo.setContents(resultSet.getString("contents"));
                return memo;
            } else {
                return null;
            }
        }, id);
    }

    /**
     * 주어진 ID에 해당하는 Memo 데이터를 데이터베이스에서 업데이트합니다.
     *
     * @param id         업데이트할 Memo의 ID
     * @param requestDto 업데이트할 Memo의 새로운 데이터를 담고 있는 DTO
     */
    public void update(Long id, MemoRequestDto requestDto) {
        String sql = "UPDATE memo SET username = ?, contents = ? WHERE id = ?";
        jdbcTemplate.update(sql, requestDto.getUsername(), requestDto.getContents(), id);
    }

    /**
     * 주어진 ID에 해당하는 Memo 데이터를 데이터베이스에서 삭제합니다.
     *
     * @param id 삭제할 Memo의 ID
     */
    public void delete(Long id) {
        String sql = "DELETE FROM memo WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    /**
     * 트랜잭션을 테스트하기 위해 임시로 만든 메서드입니다.
     * EntityManager를 사용하여 Memo 객체를 업데이트하고 반환합니다.
     *
     * @param em EntityManager 객체
     * @return 업데이트된 Memo 객체
     */
    @Transactional
    public Memo createMemo(EntityManager em) {
        Memo memo = em.find(Memo.class, 1); // ID가 1인 Memo 객체를 조회합니다.
        memo.setUsername("Robbie"); // Memo 객체의 username을 업데이트합니다.
        memo.setContents("@Transactional 전파 테스트 중!"); // Memo 객체의 contents를 업데이트합니다.

        System.out.println("createMemo 메서드 종료");
        return memo; // 업데이트된 Memo 객체를 반환합니다.
    }
}
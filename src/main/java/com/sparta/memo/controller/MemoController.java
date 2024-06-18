package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@RestController // 이 클래스가 RESTful 웹 서비스의 컨트롤러임을 나타냅니다
@RequestMapping("/api") // 이 클래스의 모든 요청 경로 앞에 "/api"가 붙습니다
public class MemoController {

    private final JdbcTemplate jdbcTemplate; // 데이터베이스 연동을 위한 JdbcTemplate 인스턴스

    // 생성자 주입을 통해 JdbcTemplate 인스턴스를 초기화합니다
    public MemoController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 새로운 메모를 생성하는 API 엔드포인트
    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {
        // 요청 DTO를 엔티티로 변환
        Memo memo = new Memo(requestDto);

        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        // SQL 쿼리 정의
        String sql = "INSERT INTO memo (username, contents) VALUES (?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, memo.getUsername());
            preparedStatement.setString(2, memo.getContents());
            return preparedStatement;
        }, keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        memo.setId(id);

        // 엔티티를 응답 DTO로 변환
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

        // 생성된 메모를 응답으로 반환
        return memoResponseDto;
    }

    // 모든 메모를 조회하는 API 엔드포인트
    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos() {
        // DB 조회
        String sql = "SELECT * FROM memo";

        // SQL 쿼리를 실행하고 결과를 MemoResponseDto 객체로 매핑
        return jdbcTemplate.query(sql, new RowMapper<MemoResponseDto>() {
            @Override
            public MemoResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
                Long id = rs.getLong("id");
                String username = rs.getString("username");
                String contents = rs.getString("contents");
                return new MemoResponseDto(id, username, contents);
            }
        });
    }

    // 메모를 수정하는 API 엔드포인트
    @PutMapping("/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = findById(id);
        if (memo != null) {
            // memo 내용 수정
            String sql = "UPDATE memo SET username = ?, contents = ? WHERE id = ?";
            jdbcTemplate.update(sql, requestDto.getUsername(), requestDto.getContents(), id);

            return id;
        } else {
            // 해당 메모가 존재하지 않으면 예외 발생
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    // 메모를 삭제하는 API 엔드포인트
    @DeleteMapping("/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = findById(id);
        if (memo != null) {
            // memo 삭제
            String sql = "DELETE FROM memo WHERE id = ?";
            jdbcTemplate.update(sql, id);

            return id;
        } else {
            // 해당 메모가 존재하지 않으면 예외 발생
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    // 주어진 ID에 해당하는 메모를 DB에서 조회하는 메서드
    private Memo findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM memo WHERE id = ?";

        // SQL 쿼리를 실행하고 결과를 Memo 객체로 매핑
        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Memo memo = new Memo();
                memo.setId(resultSet.getLong("id"));
                memo.setUsername(resultSet.getString("username"));
                memo.setContents(resultSet.getString("contents"));
                return memo;
            } else {
                return null;
            }
        }, id);
    }
}

package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.service.MemoService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 이 클래스가 REST 컨트롤러임을 선언합니다.
@RequestMapping("/api") // 이 클래스의 모든 요청은 /api 경로 아래에서 처리됩니다.
public class MemoController {

    private final JdbcTemplate jdbcTemplate; // JdbcTemplate 객체를 사용하여 데이터베이스와 상호작용합니다.

    /**
     * MemoController의 생성자입니다.
     *
     * @param jdbcTemplate JdbcTemplate 객체, 데이터베이스 쿼리 실행에 사용됩니다.
     */
    public MemoController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 새로운 Memo를 생성하는 POST 요청을 처리합니다.
     *
     * @param requestDto Memo를 생성하기 위한 요청 데이터 DTO
     * @return 생성된 Memo의 정보를 포함한 MemoResponseDto
     */
    @PostMapping("/memos") // HTTP POST 요청을 처리하며, /api/memos 경로에서 접근 가능합니다.
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {
        MemoService memoService = new MemoService(jdbcTemplate); // MemoService 객체를 생성합니다.
        return memoService.createMemo(requestDto); // Memo를 생성하고 그 결과를 MemoResponseDto로 반환합니다.
    }

    /**
     * 모든 Memo 목록을 조회하는 GET 요청을 처리합니다.
     *
     * @return Memo 목록을 담은 MemoResponseDto 리스트
     */
    @GetMapping("/memos") // HTTP GET 요청을 처리하며, /api/memos 경로에서 접근 가능합니다.
    public List<MemoResponseDto> getMemos() {
        MemoService memoService = new MemoService(jdbcTemplate); // MemoService 객체를 생성합니다.
        return memoService.getMemos(); // 모든 Memo 목록을 조회하고 그 결과를 MemoResponseDto 리스트로 반환합니다.
    }

    /**
     * 특정 Memo를 업데이트하는 PUT 요청을 처리합니다.
     *
     * @param id         업데이트할 Memo의 ID
     * @param requestDto Memo 업데이트를 위한 요청 데이터 DTO
     * @return 업데이트된 Memo의 ID
     */
    @PutMapping("/memos/{id}") // HTTP PUT 요청을 처리하며, /api/memos/{id} 경로에서 접근 가능합니다.
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        MemoService memoService = new MemoService(jdbcTemplate); // MemoService 객체를 생성합니다.
        return memoService.updateMemo(id, requestDto); // 특정 Memo를 업데이트하고 업데이트된 Memo의 ID를 반환합니다.
    }

    /**
     * 특정 Memo를 삭제하는 DELETE 요청을 처리합니다.
     *
     * @param id 삭제할 Memo의 ID
     * @return 삭제된 Memo의 ID
     */
    @DeleteMapping("/memos/{id}") // HTTP DELETE 요청을 처리하며, /api/memos/{id} 경로에서 접근 가능합니다.
    public Long deleteMemo(@PathVariable Long id) {
        MemoService memoService = new MemoService(jdbcTemplate); // MemoService 객체를 생성합니다.
        return memoService.deleteMemo(id); // 특정 Memo를 삭제하고 삭제된 Memo의 ID를 반환합니다.
    }
}

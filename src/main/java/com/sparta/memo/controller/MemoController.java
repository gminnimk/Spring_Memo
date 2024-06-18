package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import com.sparta.memo.service.MemoService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// REST API를 제공하는 컨트롤러 클래스입니다.
@RestController
@RequestMapping("/api") // 이 컨트롤러의 모든 엔드포인트는 /api로 시작합니다.
public class MemoController {
    // MemoController > MemoService > MemoRepository

    private final MemoService memoService; // MemoService 객체를 멤버 변수로 선언합니다.

    /**
     * MemoController의 생성자입니다.
     *
     * @param memoService MemoService 객체, Memo 관련 비즈니스 로직을 수행합니다.
     */
    public MemoController(MemoService memoService) {
        this.memoService = memoService; // MemoService 객체를 생성자에서 주입받아 멤버 변수에 할당합니다.
    }

    /**
     * Memo를 생성하는 POST 요청을 처리하는 메서드입니다.
     *
     * @param requestDto Memo를 생성하기 위한 요청 데이터 DTO
     * @return 생성된 Memo 정보를 포함한 MemoResponseDto
     */
    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {
        return memoService.createMemo(requestDto); // MemoService를 통해 Memo를 생성하고 생성된 MemoResponseDto를 반환합니다.
    }

    /**
     * 모든 Memo 목록을 조회하는 GET 요청을 처리하는 메서드입니다.
     *
     * @return Memo 목록을 담은 MemoResponseDto 리스트
     */
    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos() {
        return memoService.getMemos(); // MemoService를 통해 모든 Memo 목록을 조회하여 반환합니다.
    }

    /**
     * 특정 Memo를 업데이트하는 PUT 요청을 처리하는 메서드입니다.
     *
     * @param id         업데이트할 Memo의 ID
     * @param requestDto Memo 업데이트를 위한 요청 데이터 DTO
     * @return 업데이트된 Memo의 ID
     */
    @PutMapping("/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        return memoService.updateMemo(id, requestDto); // MemoService를 통해 특정 Memo를 업데이트하고 업데이트된 Memo의 ID를 반환합니다.
    }

    /**
     * 특정 Memo를 삭제하는 DELETE 요청을 처리하는 메서드입니다.
     *
     * @param id 삭제할 Memo의 ID
     * @return 삭제된 Memo의 ID
     */
    @DeleteMapping("/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        return memoService.deleteMemo(id); // MemoService를 통해 특정 Memo를 삭제하고 삭제된 Memo의 ID를 반환합니다.
    }
}
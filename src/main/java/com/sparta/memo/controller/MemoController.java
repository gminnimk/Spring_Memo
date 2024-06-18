package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.service.MemoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 처음에는 내부적으로 동작하는 원리를 깊게 이해하려 하기 보다는
// 내부적으로 사용되고 있구나 정도로 가볍게 이해하는 것이 좋습니다.

@RestController // RESTful 웹 서비스를 위한 컨트롤러임을 나타냅니다.
@RequestMapping("/api") // 모든 요청의 기본 경로를 "/api"로 설정합니다.
public class MemoController {
    // MemoController는 MemoService를 통해 MemoRepository와 소통합니다.

    private final MemoService memoService; // MemoService를 주입받습니다.

    // MemoController 생성자
    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    // 특정 객체 조회하기
    @GetMapping("/memos/contents")
    public List<MemoResponseDto> getMemosByKeyword(String keyword) {
        return memoService.getMemoByKeyword(keyword);
    }
    // 새로운 메모 생성
    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {
        return memoService.createMemo(requestDto);
    }

    // 모든 메모 조회
    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos() {
        return memoService.getMemos();
    }

    // 기존 메모 수정
    @PutMapping("/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        return memoService.updateMemo(id, requestDto);
    }

    // 기존 메모 삭제
    @DeleteMapping("/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        return memoService.deleteMemo(id);
    }
}
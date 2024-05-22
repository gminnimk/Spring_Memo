package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import com.sparta.memo.service.MemoService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 처음에는 내부적으로 동작하려는 원인을 이해랄려고 깊게 하기 보다는
// 내부적으로 사용하고 있구나 정도로 가볍게 이해하기

@RestController
@RequestMapping("/api")
public class MemoController {
    // MemoController > MemoService > MemoRepository

    private final MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }




    /*

❓ Query Methods 기능을 사용하여 내용(contents)에 특정 키워드가 포함된 메모를 조회하는 API를 구현합니다.

- Controller를 통해 keyword를 Query String 방식으로 전달받아 메모를 조회하는 API를 추가합니다.
    - http://localhost:8080/api/memos/contents?keyword=
    - 메서드명은 getMemosByKeyword 입니다.
- MemoService에 getMemosByKeyword 기능을 수행하는 메서드를 구현합니다.
- ‘Postman’을 사용해서 테스트합니다.
- 정렬 방법은 수정 시간을 기준으로 내림차순을 적용합니다.
- “Query Methods contains” 같은 검색어를 구글링하여 방법을 찾습니다.
- Spring Data JPA DOCS를 사용하여 방법을 찾습니다.

     */

    //keyword를 Query String 방식으로 전달 받아 메모를 조회하는 api 추가!
    // GetMapping 에노테이션을 사용하여서 memos에 contents를 지정
    @GetMapping("/memos/contents")
    public List<MemoResponseDto> getMemoContents(String keyword) {
        return memoService.getMemoByKeyword(keyword);
    }



    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {
        return memoService.createMemo(requestDto);
    }
    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos() {
        return memoService.getMemos();
    }

    @PutMapping("/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        return memoService.updateMemo(id, requestDto);
    }

    @DeleteMapping("/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        return memoService.deleteMemo(id);
    }
}
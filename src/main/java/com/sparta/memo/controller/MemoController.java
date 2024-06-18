package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // 특정 클래스가 RESTful 웹 서비스의 컨트롤러 역할을 한다는 것을 나타냄
@RequestMapping("/api") // 요청 URL과 컨트롤러의 핸들러 메서드를 매핑, 클래스 레벨에서 기본 URL 경로를 설정합니다
public class MemoController {

    // 메모를 저장할 메모리 내 Map 자료구조
    // Map<Long, Memo>: Long 타입의 ID를 키로 하고 Memo 객체를 값으로 가짐
    private final Map<Long, Memo> memoList = new HashMap<>();

    // 새로운 메모를 생성하는 API 엔드포인트
    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {
        // 요청 DTO를 엔티티로 변환
        // 클라이언트에서 보낸 메모 데이터를 MemoRequestDto 객체로 받음
        Memo memo = new Memo(requestDto);

        // 메모의 최대 ID를 확인하고 새로운 ID를 설정
        // memoList가 비어있지 않으면 최대 ID를 가져와 1을 더하고, 비어있으면 ID를 1로 설정
        Long maxId = memoList.size() > 0 ? Collections.max(memoList.keySet()) + 1 : 1;
        memo.setId(maxId); // 메모 객체에 ID 설정

        // 메모를 Map에 저장
        memoList.put(memo.getId(), memo); // memoList Map에 memo 객체를 추가

        // 엔티티를 응답 DTO로 변환
        // Memo 객체를 MemoResponseDto 객체로 변환하여 응답 데이터로 준비
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

        // 생성된 메모를 응답으로 반환
        // 클라이언트에 생성된 메모의 정보를 반환
        return memoResponseDto;
    }

    // 모든 메모를 조회하는 API 엔드포인트
    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos() {
        // Map에 저장된 메모들을 리스트로 변환
        // memoList의 모든 값을 가져와 MemoResponseDto 객체로 변환한 후 리스트로 만듦
        List<MemoResponseDto> responseList = memoList.values().stream()
                .map(MemoResponseDto::new).toList();

        // 변환된 메모 리스트를 응답으로 반환
        // 클라이언트에 모든 메모의 정보를 반환
        return responseList;
    }

    // 메모를 수정하는 API 엔드포인트
    @PutMapping("/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        // 해당 메모가 Map에 존재하는지 확인
        if (memoList.containsKey(id)) {
            // 해당 메모 가져오기
            Memo memo = memoList.get(id);

            // 메모 수정
            memo.update(requestDto);
            return memo.getId(); // 수정된 메모의 ID 반환
        } else {
            // 해당 메모가 존재하지 않으면 예외 발생
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    // 메모를 삭제하는 API 엔드포인트
    @DeleteMapping("/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        // 해당 메모가 Map에 존재하는지 확인
        if (memoList.containsKey(id)) {
            // 해당 메모 삭제하기
            memoList.remove(id);
            return id; // 삭제된 메모의 ID 반환
        } else {
            // 해당 메모가 존재하지 않으면 예외 발생
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }
}
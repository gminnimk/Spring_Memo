package com.sparta.memo.service;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import com.sparta.memo.repository.MemoRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MemoService {

    private final JdbcTemplate jdbcTemplate; // 데이터베이스 연동을 위한 JdbcTemplate 인스턴스

    // 생성자 주입을 통해 JdbcTemplate 인스턴스를 초기화합니다
    public MemoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 새로운 메모를 생성하는 서비스 메서드
    public MemoResponseDto createMemo(MemoRequestDto requestDto) {
        // 요청 DTO를 엔티티로 변환
        Memo memo = new Memo(requestDto);

        // 메모 저장을 위해 MemoRepository 인스턴스를 생성
        MemoRepository memoRepository = new MemoRepository(jdbcTemplate);
        Memo saveMemo = memoRepository.save(memo);

        // 저장된 엔티티를 응답 DTO로 변환
        MemoResponseDto memoResponseDto = new MemoResponseDto(saveMemo);

        // 생성된 메모를 응답으로 반환
        return memoResponseDto;
    }

    // 모든 메모를 조회하는 서비스 메서드
    public List<MemoResponseDto> getMemos() {
        // 메모 조회를 위해 MemoRepository 인스턴스를 생성
        MemoRepository memoRepository = new MemoRepository(jdbcTemplate);

        // 모든 메모를 조회하고 응답 DTO 리스트로 변환하여 반환
        return memoRepository.findAll();
    }

    // 메모를 수정하는 서비스 메서드
    public Long updateMemo(Long id, MemoRequestDto requestDto) {
        // 메모 수정 작업을 위해 MemoRepository 인스턴스를 생성
        MemoRepository memoRepository = new MemoRepository(jdbcTemplate);

        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = memoRepository.findById(id);
        if (memo != null) {
            // 메모 내용 수정
            memoRepository.update(id, requestDto);
            return id;
        } else {
            // 해당 메모가 존재하지 않으면 예외 발생
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    // 메모를 삭제하는 서비스 메서드
    public Long deleteMemo(Long id) {
        // 메모 삭제 작업을 위해 MemoRepository 인스턴스를 생성
        MemoRepository memoRepository = new MemoRepository(jdbcTemplate);

        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = memoRepository.findById(id);
        if (memo != null) {
            // 메모 삭제
            memoRepository.delete(id);
            return id;
        } else {
            // 해당 메모가 존재하지 않으면 예외 발생
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }
}

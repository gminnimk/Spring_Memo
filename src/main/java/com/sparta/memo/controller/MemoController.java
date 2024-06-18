package com.sparta.memo.service;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import com.sparta.memo.repository.MemoRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Memo 데이터 처리를 담당하는 서비스 클래스입니다.
 * Controller와 Repository 사이에서 비즈니스 로직을 수행합니다.
 */
public class MemoService {

    private final MemoRepository memoRepository; // MemoRepository 객체를 멤버 변수로 선언합니다.

    /**
     * MemoService의 생성자입니다.
     *
     * @param memoRepository MemoRepository 객체, Memo 데이터베이스 접근을 담당합니다.
     */
    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository; // MemoRepository 객체를 생성자에서 주입받아 멤버 변수에 할당합니다.
    }

    /**
     * Memo를 생성하는 메서드입니다.
     *
     * @param requestDto Memo를 생성하기 위한 요청 데이터 DTO
     * @return 생성된 Memo 정보를 포함한 MemoResponseDto
     */
    public MemoResponseDto createMemo(MemoRequestDto requestDto) {
        // MemoRequestDto를 이용하여 Memo 엔티티 객체를 생성합니다.
        Memo memo = new Memo(requestDto);

        // Memo 엔티티를 MemoRepository를 통해 데이터베이스에 저장합니다.
        Memo saveMemo = memoRepository.save(memo);

        // 저장된 Memo 엔티티를 MemoResponseDto로 변환하여 반환합니다.
        MemoResponseDto memoResponseDto = new MemoResponseDto(saveMemo);
        return memoResponseDto;
    }

    /**
     * 모든 Memo 목록을 조회하는 메서드입니다.
     *
     * @return Memo 목록을 담은 MemoResponseDto 리스트
     */
    public List<MemoResponseDto> getMemos() {
        // MemoRepository를 통해 데이터베이스에서 모든 Memo 목록을 조회하여 반환합니다.
        return memoRepository.findAll();
    }

    /**
     * 특정 Memo를 업데이트하는 메서드입니다.
     *
     * @param id         업데이트할 Memo의 ID
     * @param requestDto Memo 업데이트를 위한 요청 데이터 DTO
     * @return 업데이트된 Memo의 ID
     * @throws IllegalArgumentException 해당 ID에 해당하는 Memo가 존재하지 않을 경우 예외를 던집니다.
     */
    public Long updateMemo(Long id, MemoRequestDto requestDto) {
        // 업데이트할 Memo가 데이터베이스에 존재하는지 확인합니다.
        Memo memo = memoRepository.findById(id);
        if (memo != null) {
            // Memo 엔티티의 내용을 업데이트합니다.
            memoRepository.update(id, requestDto);

            return id; // 업데이트된 Memo의 ID를 반환합니다.
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    /**
     * 특정 Memo를 삭제하는 메서드입니다.
     *
     * @param id 삭제할 Memo의 ID
     * @return 삭제된 Memo의 ID
     * @throws IllegalArgumentException 해당 ID에 해당하는 Memo가 존재하지 않을 경우 예외를 던집니다.
     */
    public Long deleteMemo(Long id) {
        // 삭제할 Memo가 데이터베이스에 존재하는지 확인합니다.
        Memo memo = memoRepository.findById(id);
        if (memo != null) {
            // Memo를 삭제합니다.
            memoRepository.delete(id);

            return id; // 삭제된 Memo의 ID를 반환합니다.
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }
}
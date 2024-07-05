package com.sparta.memo.service;


// 실제로 우리가 저장할 수 있게 도와주는 로직



import com.sparta.memo.dto.MemoRequestDto; // MemoRequestDto를 import
import com.sparta.memo.dto.MemoResponseDto; // MemoResponseDto를 import
import com.sparta.memo.entity.Memo; // Memo 엔티티를 import
import com.sparta.memo.repository.MemoRepository; // MemoRepository를 import
import org.springframework.stereotype.Service; // Spring의 Service 어노테이션을 import
import org.springframework.transaction.annotation.Transactional; // Spring의 트랜잭션 어노테이션을 import

import java.util.List; // List를 import

@Service // Spring에게 이 클래스가 Service 역할을 하는 클래스임을 알립니다.
public class MemoService {

    private final MemoRepository memoRepository; // MemoRepository 의존성 주입을 위한 멤버 변수

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository; // MemoRepository를 주입받아 멤버 변수에 할당
    }

    public MemoResponseDto createMemo(MemoRequestDto requestDto) {
        // RequestDto -> Entity
        Memo memo = new Memo(requestDto); // Memo 엔티티 객체를 RequestDto를 이용하여 생성합니다.

        // DB 저장
        Memo saveMemo = memoRepository.save(memo); // Memo 엔티티를 저장하고 저장된 Memo 엔티티를 반환받습니다.

        // Entity -> ResponseDto
        MemoResponseDto memoResponseDto = new MemoResponseDto(saveMemo); // 저장된 Memo 엔티티를 이용하여 MemoResponseDto 객체를 생성합니다.

        return memoResponseDto; // 생성된 MemoResponseDto 객체를 반환합니다.
    }


    public List<MemoResponseDto> getMemos() {
        // DB에서 Memo 엔티티의 모든 데이터를 ModifiedAt 내림차순으로 조회
        // 각 Memo 엔티티를 MemoResponseDto로 변환한 후 리스트로 반환
        return memoRepository.findAllByOrderByModifiedAtDesc().stream()
                .map(MemoResponseDto::new)
                .toList();
    }


    // 주어진 키워드를 포함하는 Memo 엔티티들을 DB에서 조회합니다.
    // 조회된 Memo 엔티티들을 수정 시간(ModifiedAt) 내림차순으로 정렬합니다.
    // 각 Memo 엔티티를 MemoResponseDto로 변환하고, 변환된 MemoResponseDto 객체들을 리스트로 반환합니다.
    public List<MemoResponseDto> getMemosByKeyword(String keyword) {
        return memoRepository.findAllByContentsContainsOrderByModifiedAtDesc(keyword)
                .stream() // 조회된 Memo 엔티티 리스트를 스트림으로 변환합니다.
                .map(MemoResponseDto::new) // 각 Memo 엔티티를 MemoResponseDto로 변환합니다.
                .toList(); // 변환된 MemoResponseDto 객체들을 리스트로 수집합니다.
    }


    @Transactional // 메서드에 트랜잭션 처리를 적용합니다.
    public Long updateMemo(Long id, MemoRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인하고 존재하지 않으면 예외를 던집니다.
        Memo memo = findMemo(id);

        // memo 내용 수정
        memo.update(requestDto); // Memo 엔티티의 내용을 업데이트합니다.

        return id; // 업데이트된 Memo의 id를 반환합니다.
    }

    public Long deleteMemo(Long id) {
        // 해당 메모가 DB에 존재하는지 확인하고 존재하지 않으면 예외를 던집니다.
        Memo memo = findMemo(id);

        // memo 삭제
        memoRepository.delete(memo); // Memo 엔티티를 삭제합니다.

        return id; // 삭제된 Memo의 id를 반환합니다.
    }

    private Memo findMemo(Long id) {
        // 주어진 id로 Memo 엔티티를 찾아 반환하며, Memo 엔티티가 존재하지 않으면 예외를 던집니다.
        return memoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }

}
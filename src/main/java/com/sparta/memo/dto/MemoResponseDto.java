package com.sparta.memo.dto;

import com.sparta.memo.entity.Memo;
import lombok.Getter;

/**
 * Memo 정보를 응답으로 전송하기 위한 데이터 전송 객체 (DTO)입니다.
 * 이 클래스는 Memo 데이터를 구조화된 형식으로 클라이언트에 전달하기 위한 목적으로 사용됩니다.
 */
@Getter
public class MemoResponseDto {
    private Long id;         // Memo의 식별자(ID)
    private String username; // Memo를 작성한 사용자 이름
    private String contents; // Memo의 내용

    /**
     * Memo 엔티티 객체를 기반으로 MemoResponseDto를 생성합니다.
     * Memo 객체에서 필요한 필드를 복사하여 이 DTO에 할당합니다.
     *
     * @param memo 전송할 Memo 엔티티 객체
     */
    public MemoResponseDto(Memo memo) {
        this.id =  memo.getId();
        this.username = memo.getUsername();
        this.contents = memo.getContents();
    }

    /**
     * 지정된 필드 값으로 MemoResponseDto를 생성합니다.
     * (현재 구현되어 있지 않으며, 나중에 사용될 예정입니다.)
     *
     * @param id        Memo ID
     * @param username  Memo 작성자의 사용자 이름
     * @param contents  Memo 내용
     */
    public MemoResponseDto(Long id, String username, String contents) {
        // 이 생성자는 특정 값으로 MemoResponseDto를 수동으로 생성하는 데 사용될 수 있습니다.
        // 현재 제공된 코드에서는 사용되지 않고 있습니다.
    }
}

package com.sparta.memo.dto;

import com.sparta.memo.entity.Memo;
import lombok.Getter;

@Getter // Lombok의 @Getter 어노테이션으로 필드의 getter 메서드를 자동 생성합니다.
public class MemoResponseDto {
    private Long id; // Memo의 ID를 저장하는 필드입니다.
    private String username; // Memo의 작성자 이름을 저장하는 필드입니다.
    private String contents; // Memo의 내용을 저장하는 필드입니다.

    /**
     * Memo 엔티티를 기반으로 MemoResponseDto 객체를 생성하는 생성자입니다.
     *
     * @param memo Memo 엔티티 객체
     */
    public MemoResponseDto(Memo memo) {
        this.id =  memo.getId(); // Memo 엔티티에서 ID를 가져와 필드에 할당합니다.
        this.username = memo.getUsername(); // Memo 엔티티에서 작성자 이름을 가져와 필드에 할당합니다.
        this.contents = memo.getContents(); // Memo 엔티티에서 내용을 가져와 필드에 할당합니다.
    }

    /**
     * ID, 작성자 이름, 내용을 직접 지정하여 MemoResponseDto 객체를 생성하는 생성자입니다.
     *
     * @param id       Memo의 ID
     * @param username Memo의 작성자 이름
     * @param contents Memo의 내용
     */
    public MemoResponseDto(Long id, String username, String contents) {
        this.id = id; // 주어진 ID를 필드에 할당합니다.
        this.username = username; // 주어진 작성자 이름을 필드에 할당합니다.
        this.contents = contents; // 주어진 내용을 필드에 할당합니다.
    }
}
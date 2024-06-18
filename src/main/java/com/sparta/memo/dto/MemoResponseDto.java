package com.sparta.memo.dto;

import com.sparta.memo.entity.Memo; // Memo 엔티티를 import
import lombok.Getter; // Lombok의 Getter 어노테이션을 import

import java.time.LocalDateTime; // LocalDateTime을 import

@Getter // Lombok의 Getter 메서드를 자동 생성하는 어노테이션
public class MemoResponseDto {
    private Long id; // Memo의 id를 저장하는 필드
    private String username; // Memo의 작성자 이름을 저장하는 필드
    private String contents; // Memo의 내용을 저장하는 필드
    private LocalDateTime createdAt; // Memo의 생성일시를 저장하는 필드
    private LocalDateTime modifiedAt; // Memo의 수정일시를 저장하는 필드

    // Memo 엔티티를 기반으로 MemoResponseDto 객체를 생성하는 생성자
    public MemoResponseDto(Memo memo) {
        this.id = memo.getId(); // Memo 엔티티에서 id를 가져와 할당합니다.
        this.username = memo.getUsername(); // Memo 엔티티에서 username을 가져와 할당합니다.
        this.contents = memo.getContents(); // Memo 엔티티에서 contents를 가져와 할당합니다.
        this.createdAt = memo.getCreatedAt(); // Memo 엔티티에서 createdAt을 가져와 할당합니다.
        this.modifiedAt = memo.getModifiedAt(); // Memo 엔티티에서 modifiedAt을 가져와 할당합니다.
    }
}

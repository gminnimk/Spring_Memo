package com.sparta.memo.dto;

import com.sparta.memo.entity.Memo;
import lombok.Getter;

@Getter
public class MemoResponseDto {
    // 메모의 고유 ID
    private Long id;

    // 메모를 작성한 사용자 이름
    private String username;

    // 메모의 내용
    private String contents;

    // Memo 엔티티 객체를 받아서 MemoResponseDto 객체를 생성하는 생성자
    public MemoResponseDto(Memo memo) {
        // Memo 엔티티로부터 ID를 가져와서 설정
        this.id = memo.getId();
        // Memo 엔티티로부터 사용자 이름을 가져와서 설정
        this.username = memo.getUsername();
        // Memo 엔티티로부터 메모 내용을 가져와서 설정
        this.contents = memo.getContents();
    }

    public MemoResponseDto(Long id, String username, String contents) {
        this.id = id;
        this.username = username;
        this.contents = contents;

    }
}
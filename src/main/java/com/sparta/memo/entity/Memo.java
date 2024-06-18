package com.sparta.memo.entity;

import com.sparta.memo.dto.MemoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // Lombok의 애노테이션으로, 클래스 내 모든 필드에 대해 getter 메서드를 자동으로 생성. 이로 인해 직접 getId(), getUsername(), getContents() 메서드를 작성할 필요 X
@Setter // Lombok의 애노테이션으로, 클래스 내 모든 필드에 대해 setter 메서드를 자동으로 생성. 이로 인해 직접 setId(), setUsername(), setContents() 메서드를 작성할 필요 X
@NoArgsConstructor // Lombok의 애노테이션으로, 파라미터가 없는 기본 생성자를 자동으로 생성.
public class Memo {
    // 메모의 고유 ID
    private Long id;

    // 메모를 작성한 사용자 이름
    private String username;

    // 메모의 내용
    private String contents;

    // MemoRequestDto를 받아서 Memo 객체를 생성하는 생성자
    public Memo(MemoRequestDto requestDto) {
        // MemoRequestDto로부터 사용자 이름을 가져와서 설정
        this.username = requestDto.getUsername();
        // MemoRequestDto로부터 메모 내용을 가져와서 설정
        this.contents = requestDto.getContents();
    }

    public void update(MemoRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }
}
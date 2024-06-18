package com.sparta.memo.entity;

import com.sparta.memo.dto.MemoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // JPA가 관리할 수 있는 엔티티 클래스임을 나타냅니다.
@Getter // Lombok의 @Getter 어노테이션으로 필드의 getter 메서드를 자동 생성합니다.
@Setter // Lombok의 @Setter 어노테이션으로 필드의 setter 메서드를 자동 생성합니다.
@Table(name = "memo") // 데이터베이스 테이블과 매핑될 이름을 지정합니다.
@NoArgsConstructor // Lombok의 기본 생성자 자동 생성 어노테이션입니다.
public class Memo {
    @Id // Primary Key임을 나타내는 어노테이션입니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성되는 값을 가지는 Primary Key를 나타냅니다.
    private Long id; // Memo의 ID를 저장하는 필드입니다.

    @Column(name = "username", nullable = false) // 데이터베이스 테이블의 컬럼을 나타내며, 이름과 null 허용 여부를 지정합니다.
    private String username; // Memo의 작성자 이름을 저장하는 필드입니다.

    @Column(name = "contents", nullable = false, length = 500) // 데이터베이스 테이블의 컬럼을 나타내며, 이름, null 허용 여부, 최대 길이를 지정합니다.
    private String contents; // Memo의 내용을 저장하는 필드입니다.

    /**
     * MemoRequestDto를 이용해 Memo 엔티티 객체를 생성하는 생성자입니다.
     *
     * @param requestDto Memo 생성을 위한 요청 데이터 DTO
     */
    public Memo(MemoRequestDto requestDto) {
        this.username = requestDto.getUsername(); // MemoRequestDto에서 받아온 작성자 이름을 필드에 할당합니다.
        this.contents = requestDto.getContents(); // MemoRequestDto에서 받아온 내용을 필드에 할당합니다.
    }

    /**
     * Memo 엔티티 객체를 업데이트하는 메서드입니다.
     *
     * @param requestDto Memo 업데이트를 위한 요청 데이터 DTO
     */
    public void update(MemoRequestDto requestDto) {
        this.username = requestDto.getUsername(); // MemoRequestDto에서 받아온 작성자 이름으로 업데이트합니다.
        this.contents = requestDto.getContents(); // MemoRequestDto에서 받아온 내용으로 업데이트합니다.
    }
}
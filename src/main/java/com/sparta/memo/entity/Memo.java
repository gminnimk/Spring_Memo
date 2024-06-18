package com.sparta.memo.entity;

import com.sparta.memo.dto.MemoRequestDto; // MemoRequestDto 클래스를 import
import jakarta.persistence.*; // JPA 관련 어노테이션을 import
import lombok.Getter; // Lombok의 Getter 어노테이션을 import
import lombok.NoArgsConstructor; // Lombok의 NoArgsConstructor 어노테이션을 import
import lombok.Setter; // Lombok의 Setter 어노테이션을 import

import java.sql.Time; // Time 클래스를 import

@Entity // JPA가 관리할 수 있는 엔티티 클래스임을 지정합니다.
@Getter // Lombok으로 자동 생성된 Getter 메서드를 지정합니다.
@Setter // Lombok으로 자동 생성된 Setter 메서드를 지정합니다.
@Table(name = "memo") // 데이터베이스에서 매핑할 테이블의 이름을 지정합니다.
@NoArgsConstructor // Lombok으로 기본 생성자를 자동 생성합니다.
public class Memo extends Timestamped { // Timestamped 클래스를 상속받는 Memo 클래스입니다.

    @Id // Primary Key 필드임을 나타냅니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 데이터베이스가 자동으로 생성하는 값으로 설정합니다.
    private Long id; // 메모의 고유 식별자입니다.

    @Column(name = "username", nullable = false) // 데이터베이스에서 매핑할 열의 이름과 제약 조건을 지정합니다.
    private String username; // 메모를 작성한 사용자의 이름입니다.

    @Column(name = "contents", nullable = false, length = 500) // 데이터베이스에서 매핑할 열의 이름과 제약 조건을 지정합니다.
    private String contents; // 메모의 내용입니다.

    // MemoRequestDto를 기반으로 Memo 객체를 생성하는 생성자입니다.
    public Memo(MemoRequestDto requestDto) {
        this.username = requestDto.getUsername(); // 요청 DTO에서 username을 가져와 필드에 할당합니다.
        this.contents = requestDto.getContents(); // 요청 DTO에서 contents를 가져와 필드에 할당합니다.
    }

    // MemoRequestDto를 기반으로 Memo 객체의 필드를 업데이트하는 메서드입니다.
    public void update(MemoRequestDto requestDto) {
        this.username = requestDto.getUsername(); // 요청 DTO에서 username을 가져와 필드를 업데이트합니다.
        this.contents = requestDto.getContents(); // 요청 DTO에서 contents를 가져와 필드를 업데이트합니다.
    }
}

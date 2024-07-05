package com.sparta.memo.entity;


/*
✅ Entity 클래스는 실제 DB 테이블과 매핑되는 핵심 클래스.

    - DB의 테이블에 존재하는 컬럼들을 필드로 가지는 객체.

    - DB의 테이블과 1:1로 매핑되며, 테이블이 가지지 않는 컬럼을 필드로 가져서는 안 됨.

    - Entity는 데이터베이스 영속성(persistent)의 목적으로 사용되는 객체이며, 때문에 요청(Request)이나 응답(Response) 값을 전달하는 클래스로 사용하는 것은 좋지 않습니다.


    ➕ Entity에서는 setter 메서드의 사용을 지양해야 합니다.

        - 이유는 변경되지 않는 인스턴스에 대해서도 setter로 접근이 가능해지기 때문에 객체의 일관성, 안전성을 보장하기 힘들어집니다.
          ➡️ ( setter 메서드가 있다는 것은 불변하지 않다는 것 )


        ➕ setter 메서드가 아닌 생성자를 이용해서 초기화하는 경우 불변 객체로 활용할 수 있고,
           불변 객체로 만들면 데이터를 전달하는 과정에서 데이터가 변조되지 않음을 보장할 수 있습니다.

        ➕ Builder를 사용하면 멤버 변수가 많아지더라도 어떤 값을 어떤 필드에 넣는지 코드를 통해 확인할 수 있고,
            필요한 값만 넣는 것이 가능하다는 장점이 있습니다.
*/




import com.sparta.memo.dto.MemoRequestDto; // MemoRequestDto 클래스를 import
import jakarta.persistence.*; // JPA 관련 어노테이션을 import
import lombok.Getter; // Lombok의 Getter 어노테이션을 import
import lombok.NoArgsConstructor; // Lombok의 NoArgsConstructor 어노테이션을 import
import lombok.Setter; // Lombok의 Setter 어노테이션을 import


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

package com.sparta.memo.entity;

import jakarta.persistence.*; // JPA 관련 어노테이션을 import
import lombok.Getter; // Lombok의 Getter 어노테이션을 import
import org.springframework.data.annotation.CreatedDate; // Spring Data에서 제공하는 CreatedDate 어노테이션을 import
import org.springframework.data.annotation.LastModifiedDate; // Spring Data에서 제공하는 LastModifiedDate 어노테이션을 import
import org.springframework.data.jpa.domain.support.AuditingEntityListener; // Spring Data JPA에서 제공하는 AuditingEntityListener 클래스를 import

import java.time.LocalDateTime; // Java 8에서 제공하는 LocalDateTime 클래스를 import

@Getter // Lombok으로 자동 생성된 Getter 메서드를 지정합니다.
@MappedSuperclass // 이 클래스를 통해 매핑할 컬럼을 자식 클래스에 상속합니다.
@EntityListeners(AuditingEntityListener.class) // 엔티티의 변경 사항을 추적하는 AuditingEntityListener를 지정합니다.
public abstract class Timestamped { // 추상 클래스인 Timestamped를 선언합니다.

    @CreatedDate // 엔티티가 생성될 때 자동으로 날짜와 시간을 기록하는 어노테이션입니다.
    @Column(updatable = false) // 업데이트할 수 없는 컬럼임을 나타냅니다.
    @Temporal(TemporalType.TIMESTAMP) // 데이터베이스에서 사용할 시간 타입을 지정합니다.
    private LocalDateTime createdAt; // 생성일자를 저장하는 필드입니다.

    @LastModifiedDate // 엔티티가 수정될 때 자동으로 날짜와 시간을 기록하는 어노테이션입니다.
    @Column // 기본적으로 모든 필드에 적용되는 Column 어노테이션입니다.
    @Temporal(TemporalType.TIMESTAMP) // 데이터베이스에서 사용할 시간 타입을 지정합니다.
    private LocalDateTime modifiedAt; // 최종 수정일자를 저장하는 필드입니다.
}

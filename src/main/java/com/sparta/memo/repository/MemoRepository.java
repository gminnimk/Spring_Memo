package com.sparta.memo.repository;

import com.sparta.memo.entity.Memo; // Memo 엔티티를 import

import org.springframework.data.jpa.repository.JpaRepository; // JpaRepository 인터페이스를 import

/**
 * Memo 엔티티를 관리하기 위한 Spring Data JPA Repository 인터페이스입니다.
 * JpaRepository를 상속받아 Memo 엔티티에 대한 기본적인 CRUD 작업을 자동으로 처리합니다.
 */
public interface MemoRepository extends JpaRepository<Memo, Long> {

}

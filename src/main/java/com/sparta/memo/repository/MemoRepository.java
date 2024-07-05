package com.sparta.memo.repository;

// Memo Entity를 가지고 저장할 수 있게 기능을 가져다 주는 클래스.

import com.sparta.memo.entity.Memo; // Memo 엔티티를 import
import org.springframework.data.jpa.repository.JpaRepository; // Spring Data JPA의 JpaRepository를 import

import java.util.List; // List 클래스를 import

// JpaRepository를 확장하여 Memo 엔티티의 리포지토리를 정의하는 인터페이스
public interface MemoRepository extends JpaRepository<Memo, Long> {

    // modifiedAt 필드를 기준으로 내림차순으로 모든 Memo 엔티티를 조회하는 메서드
    List<Memo> findAllByOrderByModifiedAtDesc();

    // 키워드를 포함하는 내용(Contents)을 가진 모든 Memo 엔티티를 수정 시간(ModifiedAt) 내림차순으로 조회합니다.
    // 반환되는 리스트는 키워드를 포함하는 Memo 엔티티의 리스트입니다.
    List<Memo> findAllByContentsContainsOrderByModifiedAtDesc(String keyword);



}
package com.sparta.memo.repository;

import com.sparta.memo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    // 수정된 시간을 기준으로 내림차순으로 모든 메모를 조회합니다.
    List<Memo> findAllByOrderByModifiedAtDesc();

    // 특정 키워드를 포함하는 메모를 수정된 시간을 기준으로 내림차순으로 조회합니다.
    List<Memo> findAllByContentsContainsOrderByModifiedAtDesc(String keyword);
}
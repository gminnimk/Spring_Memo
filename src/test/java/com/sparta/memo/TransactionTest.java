package com.sparta.memo;

import com.sparta.memo.entity.Memo;
import com.sparta.memo.repository.MemoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransactionTest {

    @PersistenceContext
    EntityManager em; // EntityManager 주입

    @Autowired
    MemoRepository memoRepository; // MemoRepository 주입

    @Test
    @Transactional // 트랜잭션 설정
    @Rollback(value = false) // 테스트 완료 후 롤백 여부 설정 (false로 설정하면 롤백하지 않음)
    @DisplayName("메모 생성 성공")
    void test1() {
        Memo memo = new Memo();
        memo.setUsername("Robbert");
        memo.setContents("@Transactional 테스트 중!");

        em.persist(memo); // 영속성 컨텍스트에 Memo 엔티티 객체 저장
    }

    @Test
    @Disabled // 이 테스트는 비활성화 상태로 설정
    @DisplayName("메모 생성 실패")
    void test2() {
        Memo memo = new Memo();
        memo.setUsername("Robbie");
        memo.setContents("@Transactional 테스트 중!");

        em.persist(memo); // 영속성 컨텍스트에 Memo 엔티티 객체 저장
    }

    @Test
    @Disabled // 이 테스트는 비활성화 상태로 설정
//    @Transactional
//    @Rollback(value = false)
    @DisplayName("트랜잭션 전파 테스트")
    void test3() {
//        memoRepository.createMemo(em);
        System.out.println("테스트 test3 메서드 종료");
    }
}

package com.sparta.memo.dto;


/*
✅ DTO는 계층간 데이터 교환이 이루어질 수 있도록 하는 객체.

    - Controller 같은 클라이언트 단과 직접 마주하는 계층에서는 Entity 대신 DTO를 사용해서 데이터를 교환!

    - DTO는 getter, setter 메서드를 포함하며, 이 외의 비즈니스 로직은 포함하지 않음.

 */

import lombok.Getter;

@Getter
public class MemoRequestDto {
    // 클라이언트가 메모를 생성할 때 제공하는 사용자 이름
    private String username;

    // 클라이언트가 메모를 생성할 때 제공하는 메모 내용
    private String contents;
}

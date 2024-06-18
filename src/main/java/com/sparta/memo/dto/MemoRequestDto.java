package com.sparta.memo.dto;

import lombok.Getter;

@Getter
public class MemoRequestDto {
    // 클라이언트가 메모를 생성할 때 제공하는 사용자 이름
    private String username;

    // 클라이언트가 메모를 생성할 때 제공하는 메모 내용
    private String contents;
}

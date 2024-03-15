package com.springblogproj.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 클라이언트로부터 받은 Blog 정보를 DB에 보내기 위한 DTO
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BlogRequest {
    private String title;
    private String context;
}

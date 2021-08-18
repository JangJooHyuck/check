package com.example.demo.service;

import org.springframework.stereotype.Repository;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Repository
@Getter
@Setter
@NoArgsConstructor
public class Paging {

    // 페이징 데이타
    int currentPage; // 현재페이지
    int totalPage; // 총페이지

    @Builder
    public Paging(int currentPage, int totalPage) {
        this.currentPage = currentPage;
        this.totalPage = totalPage;
    }

}

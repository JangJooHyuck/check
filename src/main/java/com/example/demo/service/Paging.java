package com.example.demo.service;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Repository
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paging {

    //페이징 데이타
    int CurrentPage; // 현재페이지
    int TotalPage; // 총페이지
}

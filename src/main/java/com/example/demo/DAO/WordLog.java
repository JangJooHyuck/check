package com.example.demo.DAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// DB에 wordLog 라는 테이블 생성
@Getter
@NoArgsConstructor
@Entity
@Table(name = "word_log")
public class WordLog extends createTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idx;

    @Column
    Long id;

    @Column
    String word;

    @Builder
    public WordLog(Long id, String word) {
        this.id = id;
        this.word = word;
    }
}

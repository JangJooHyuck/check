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

// jpa 는 데이터베이스에 작성되는 테이블을 다루기 위해
// Entity라는 클래스를 사용함

@Getter
@NoArgsConstructor
@Entity
@Table(name = "userlog")
// TimeEntity 상속 date 관련
public class Userlog extends baseTimeEntity {

    // 기본키
    @Id
    // 기본키 생성을 데이터베이스에 위임 (IDENTITY (default 값은 auto))
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String UserIP;

    @Column
    private String Mail;

    @Column
    private String Result;

    @Builder
    public Userlog(String UserIP, String Mail, String Result) {

        this.UserIP = UserIP;
        this.Mail = Mail;
        this.Result = Result;

    }

}

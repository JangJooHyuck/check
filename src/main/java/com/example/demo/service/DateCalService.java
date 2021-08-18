package com.example.demo.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class DateCalService {
    // 시작하는 날짜 리턴
    // Daily는 오늘, Week는 7일전, Month는 30일전

    public String findStartDate(String period) {

        if (period.equals("Daily")) {
            return LocalDate.now().toString();
        } else if (period.equals("Week")) {
            return LocalDate.now().minusDays(7).toString();
        } else {
            return LocalDate.now().minusDays(30).toString();
        }

    }

    // 현재 날짜 day+1 반환
    public String findEndDate() {
        return LocalDate.now().plusDays(1).toString();
    }
}

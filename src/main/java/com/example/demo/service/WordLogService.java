package com.example.demo.service;

import java.util.List;

import com.example.demo.DAO.WordLog;
import com.example.demo.DAO.WordLogRepository;
import com.example.demo.DAO.WordRank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// wordlogrepository를 서비스로 연결시켜 컨트롤러에 사용할 수 있게 한다.
@Service
public class WordLogService {

    @Autowired
    WordLogRepository wordLogRepository;

    public void save(WordLog wordLog) {
        wordLogRepository.save(wordLog);
    }

    public List<WordRank> findWordRank(String startDate, String endDate, int start) {
        return wordLogRepository.findWordRank(startDate, endDate, start);
    }

    public int findCountByWordRank(String startDate, String endDate) {
        return wordLogRepository.findCountByWordRank(startDate, endDate);
    }

    public Long deleteByWord(String word) {
        return wordLogRepository.deleteByWord(word);
    }

}

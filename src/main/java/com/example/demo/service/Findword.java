package com.example.demo.service;

import com.example.demo.DAO.WordRepository;

import org.jsoup.nodes.Document;

import com.example.demo.DAO.Word;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Findword {

    @Autowired
    private WordRepository wordRepository;

    // db에 word 객체를 저장해주는 서비스
    public void save(Word word) {
        wordRepository.save(word);
    }

    // jsoup library로 단어사전에 ul태그 list_search에 있는 첫번째 텍스트 리턴
    public String getContent(String word) throws Exception {
        String url = "http://dic.daum.net/search.do?q=" + word;
        Document doc = Jsoup.connect(url).get();
        return doc.select("ul.list_search").first().text();
    }

    public void updateWord(String word, String content) {
        wordRepository.updateContent(word, content);
    }

    public long deleteByWord(String word) {
        return wordRepository.deleteByWord(word);
    }

    // 단어로 db에 저장된 데이터를 불러오는 서비스
    public Word findByWord(String word) throws Exception {
        return wordRepository.findByWord(word);
    }

}

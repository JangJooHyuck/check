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

    public void save(Word word){
        wordRepository.save(word);
    }

    public String getContent(String word) throws Exception{
        String url = "http://dic.daum.net/search.do?q=" + word;
        Document doc = Jsoup.connect(url).get();
        return doc.select("ul.list_search").first().text();
    }

    public Word findByWord(String word) throws Exception{
        return wordRepository.findByWord(word);
    }
}

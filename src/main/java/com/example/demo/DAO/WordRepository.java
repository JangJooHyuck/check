package com.example.demo.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long>{

    Word findByWord(String word);
}

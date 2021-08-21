package com.example.demo.DAO;

import javax.transaction.Transactional;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)

    @Query("Update Word w SET w.content = :content WHERE w.word = :word")
    void updateContent(@Param("word") String word, @Param("content") String content);

    Word findByWord(String word);

    @Transactional
    Long deleteByWord(String word);
}

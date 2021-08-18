package com.example.demo.DAO;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//wordlogrepository  wordlog db 에 jpa 쿼리를 던져 값을 가져온다
@Repository
public interface WordLogRepository extends JpaRepository<WordLog, Long> {

        @Query(value = "select @ROWNUM\\:=@ROWNUM+1 as idx, t.* " + "from" + "(" + "select word, count(word) as count "
                        + "from word_log " + "where created_date " + "between :startDate and :endDate "
                        + "group by word " + "order by count " + "desc " + "limit :start, 10" + ")t, "
                        + "(select @ROWNUM\\:=:start) R ", nativeQuery = true)
        List<WordRank> findWordRank(@Param("startDate") String startDate, @Param("endDate") String endDate,
                        @Param("start") int start);

        @Query(value = "select count(t.word) " + "from" + "(" + "select word " + "from word_log "
                        + "where created_date " + "between :startDate and :endDate " + "group by word "
                        + ")t", nativeQuery = true)
        int findCountByWordRank(@Param("startDate") String startDate, @Param("endDate") String endDate);

        @Transactional
        Long deleteByWord(String word);
}

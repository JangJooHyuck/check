package com.example.demo.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//<사용하고자 하는 엔티티,식별자타입>
@Repository
public interface UserlogRepository extends JpaRepository<Userlog, Long> {

    Userlog findFirstByOrderByIdDesc();

    // pageable 데이터를 인자로 받아 page<UserLog>를 리턴해 페이징에 도움을 주는 JPA
    Page<Userlog> findAll(Pageable pageable);
}

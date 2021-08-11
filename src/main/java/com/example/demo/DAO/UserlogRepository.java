package com.example.demo.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
                                                //<사용하고자 하는 엔티티,식별자타입>
public interface UserlogRepository extends JpaRepository<Userlog, Long> {

    Userlog findFirstByOrderByIdDesc();
}

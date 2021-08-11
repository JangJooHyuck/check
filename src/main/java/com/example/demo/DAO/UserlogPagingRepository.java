package com.example.demo.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserlogPagingRepository extends PagingAndSortingRepository<Userlog, Long>{
    Page<Userlog> findAll(Pageable pageable);
}

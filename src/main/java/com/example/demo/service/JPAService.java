package com.example.demo.service;

import com.example.demo.DAO.Userlog;
import com.example.demo.DAO.UserlogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class JPAService {

    @Autowired
    private UserlogRepository userlogRepository;

    public void save(Userlog userlog) {
        userlogRepository.save(userlog);
    }

    public Userlog findFirstByOrderByIdDesc() {
        return userlogRepository.findFirstByOrderByIdDesc();
    }

    public Page<Userlog> findAll(Pageable pageable) {
        return userlogRepository.findAll(pageable);
    }

}

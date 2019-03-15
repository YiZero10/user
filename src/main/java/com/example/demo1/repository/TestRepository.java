package com.example.demo1.repository;

import com.example.demo1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TestRepository extends JpaRepository<User,Long> {

    @Query("select s from User s where s.name=?1 and s.password=?2")
    public User findBynameAndPassword(String name,String password);
    @Query("select s from User s where s.name=?1")
    public User findByName(String name);

    public User findByname(String name);
    
    public String findUserByEmail(String email);
    
}

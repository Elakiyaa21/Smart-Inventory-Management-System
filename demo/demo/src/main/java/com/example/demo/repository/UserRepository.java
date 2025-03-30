package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long >{
    @Query("SELECT u from User u where u.username LIKE %:name%")
    List<User>findByUsername(@Param("name") String name);


    public List<User> findByemail(String email);




    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
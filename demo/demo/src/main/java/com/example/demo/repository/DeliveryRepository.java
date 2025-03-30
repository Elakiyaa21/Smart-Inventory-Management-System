package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Delivery;
@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long>{
    public List<Delivery> findBystatus(String status);
    
    @Query("SELECT d FROM Delivery d WHERE d.recipient LIKE %:recipient%")
    public List<Delivery>findByRecipient(String recipient);

}
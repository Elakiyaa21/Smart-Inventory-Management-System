package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{
    public List<Payment> findBymemberId(long memberId);

    @Query("SELECT p FROM Payment p WHERE p.amount > :amount")
    List<Payment>findByamount(double amount);
}
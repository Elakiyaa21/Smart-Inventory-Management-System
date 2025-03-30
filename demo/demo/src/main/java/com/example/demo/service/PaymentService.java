package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Payment;
import com.example.demo.repository.PaymentRepository;



@Service
public class PaymentService {
    @Autowired
    PaymentRepository repo;
    public Payment createPayment( Payment p)
    {
        validatePayment(p);
        return repo.save(p);
    }
    private void validatePayment(Payment payment) {
        if (payment.getAmount() == null || payment.getAmount() <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero");
        }
        if (payment.getPaymentMethod() == null || payment.getPaymentMethod().isEmpty()) {
            throw new IllegalArgumentException("Payment method cannot be empty");
        }
    }
    public List<Payment>getAllPayments()
    {
        return repo.findAll();
    }
    public String DeleteById(long id)
    {
        repo.deleteById(id);
        return "Success";
    }
    public Optional<Payment> getById(long id)
    {
        return repo.findById(id);
    }
    public Payment UpdatePayment(Long id,Payment p)
    {
        p.setId(id);
        return repo.save(p);
    }
    public Page<Payment> getPaymentByPage(int page,int size)
    {
        Pageable pageable=PageRequest.of(page, size);
        return repo.findAll(pageable);
    }
    public List<Payment>sortByPayment()
    {
       return repo.findAll(Sort.by(Sort.Direction.ASC, "amount"));
    }
    public List<Payment> getBymemberId(long memberId)
    {
        return repo.findBymemberId(memberId);
    }
    public List<Payment>getByamount(double amount)
    {
        return repo.findByamount(amount);
    }
}
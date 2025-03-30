package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Payment;
import com.example.demo.service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    PaymentService serv;
    @PostMapping
    public ResponseEntity<Object> insertPayment(@RequestBody Payment payment) {
        try {
            Payment savedPayment = serv.createPayment(payment);
            return ResponseEntity.ok(savedPayment);
        } catch (IllegalArgumentException e) {
            // Catch validation error and return a proper response
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    // public ResponseEntity<String> insertPayment(@Valid @RequestBody Payment p)
    // {
    //     //return serv.createPayment(p);
    //     serv.createPayment(p);
    //     return ResponseEntity.ok("Payment processed successfully");
    // }
    @GetMapping
    public List<Payment>getPayment()
    {
        return serv.getAllPayments();
    }
    @GetMapping("/{id}")
    public Optional<Payment> getPaymentById(@PathVariable Long id)
    {
        return serv.getById(id);
    }
    @PutMapping("/{id}")
    public Payment UpdatePayment(@PathVariable Long id,@RequestBody Payment u)
    {
        return serv.UpdatePayment(id,u);
    }
    @DeleteMapping("/{id}")
    public String DeletePayment(@PathVariable Long id)
    {
        return serv.DeleteById(id);
    }
    @GetMapping("/page")
    public Page<Payment>getByPage(@RequestParam (defaultValue="0") int page,@RequestParam (defaultValue = "5") int size)
    {
        return serv.getPaymentByPage(page, size);
    }
    @GetMapping("/sort")
    public List<Payment>sortByPayment()
    {
        return serv.sortByPayment();
    }
    @GetMapping("/custom/{memberId}")
    public List<Payment> findBymemberId(@PathVariable long memberId)
    {
        return serv.getBymemberId(memberId);
    }
    @GetMapping("/jpql/{amount}")
    public List<Payment>getByAmount(@PathVariable double amount)
    {
        return serv.getByamount(amount);
    }
}
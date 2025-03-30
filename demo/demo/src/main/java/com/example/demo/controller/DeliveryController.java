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

import com.example.demo.entity.Delivery;
import com.example.demo.service.DeliveryService;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {
    @Autowired
    DeliveryService serv;
    @PostMapping
    public ResponseEntity<Object> insertDelivery(@RequestBody Delivery delivery) {
        try {
            Delivery savedDelivery = serv.createDelivery(delivery);
            return ResponseEntity.ok(savedDelivery);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    // public Delivery insertUser(@RequestBody Delivery u)
    // {
    //     return serv.createDelivery(u);
    // }
    @GetMapping
    public List<Delivery>getDelivery()
    {
        return serv.getAllDelivery();
    }
    @GetMapping("/{id}")
    public Optional<Delivery> getDeliveryById(@PathVariable Long id)
    {
        return serv.getById(id);
    }
    @PutMapping("/{id}")
    public Delivery UpdateDelivery(@PathVariable Long id,@RequestBody Delivery u)
    {
        return serv.UpdateDelivery(id,u);
    }
    @DeleteMapping("/{id}")
    public String DeleteEmployee(@PathVariable Long id)
    {
        return serv.DeleteById(id);
    }
    @GetMapping("/page")
    public Page<Delivery>getByPage(@RequestParam (defaultValue="0") int page,@RequestParam (defaultValue = "5") int size)
    {
        return serv.getDeliveryByPage(page, size);
    }
    @GetMapping("/sort")
    public List<Delivery>sortByDelivery()
    {
        return serv.sortByDelivery();
    }
    @GetMapping("/custom/{status}")
    public List<Delivery> findByStatus(@PathVariable String status)
    {
        return serv.getByStatus(status);
    }
    @GetMapping("/jpql/{recipient}")
    public List<Delivery>findByRecipient(@PathVariable String recipient)
    {
        return serv.getByRecipient(recipient);
    }
}
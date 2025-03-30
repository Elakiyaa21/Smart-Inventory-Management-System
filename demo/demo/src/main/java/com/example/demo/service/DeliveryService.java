package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Delivery;
import com.example.demo.repository.DeliveryRepository;

@Service
public class DeliveryService {
    @Autowired
    DeliveryRepository repo;
    public void validateDelivery(Delivery delivery) {
        
        if (delivery.getStatus() == null || (!delivery.getStatus().equalsIgnoreCase("pending") &&
                !delivery.getStatus().equalsIgnoreCase("shipped") &&
                !delivery.getStatus().equalsIgnoreCase("in transit") &&
                !delivery.getStatus().equalsIgnoreCase("delivered"))) {
            throw new IllegalArgumentException("Invalid status. Allowed values: 'pending', 'shipped', 'delivered','in transit'.");
        }
    }
    public Delivery createDelivery(Delivery u)
    {
        validateDelivery(u);
        return repo.save(u);
    }
    public List<Delivery>getAllDelivery()
    {
        return repo.findAll();
    }
    public String DeleteById(long id)
    {
        repo.deleteById(id);
        return "Success";
    }
    public Optional<Delivery> getById(long id)
    {
        return repo.findById(id);
    }
    public Delivery UpdateDelivery(Long id,Delivery d)
    {
        d.setId(id);
        return repo.save(d);
    }
    public Page<Delivery> getDeliveryByPage(int page,int size)
    {
        Pageable pageable=PageRequest.of(page, size);
        return repo.findAll(pageable);
    }
    public List<Delivery>sortByDelivery()
    {
       // return userrepo.findAll(Sort.by(Sort.Direction.ASC, properties:"username"));
       return repo.findAll(Sort.by(Sort.Direction.ASC, "deliveryDate"));
    }
    public List<Delivery> getByStatus(String status)
    {
        return repo.findBystatus(status);
    }

    public List<Delivery>getByRecipient(String recipient)
    {
        return repo.findByRecipient(recipient);
    }
}
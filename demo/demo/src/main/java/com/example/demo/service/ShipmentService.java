package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Shipment;
import com.example.demo.repository.ShipmentRepository;

@Service
public class ShipmentService {
    @Autowired
    ShipmentRepository repo;
    public boolean isValidShipmentDate(Shipment shipment) {
        return shipment.getShipmentDate().before(shipment.getEstimatedDeliveryDate());
    }
    public List<Shipment>getAllShipment()
    {
        return repo.findAll();
    }
    public Shipment createShipment(Shipment s)
    {
        return repo.save(s);
    }
    public Optional<Shipment>getById(long id)
    {
        return repo.findById(id);
    }
    public Shipment UpdateShipment(Long id,Shipment s)
    {
        s.setId(id);
        return repo.save(s);
    }
    public String deleteById(long id)
    {
        repo.deleteById(id);
        return "Sucess";
    }
    public Page<Shipment>getShipmentByPage(int page,int size)
    {
        Pageable pagea=PageRequest.of(page, size);
        return repo.findAll(pagea);
    }
    public List<Shipment>sortByShipment()
    {
       return repo.findAll(Sort.by(Sort.Direction.ASC, "origin"));
    }
    public List<Shipment> getByCost(double cost)
    {
        return repo.findBycost(cost);
    }
    public List<Shipment>getBystatus(String status)
    {
        return repo.findBystatus(status);
    }
}
package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Shipment;


@Repository
public interface ShipmentRepository extends JpaRepository<Shipment,Long>{
    public List<Shipment> findBycost(double cost);

    
    @Query("SELECT s FROM Shipment s WHERE s.status = :status")
    List<Shipment>findBystatus(String status);
}
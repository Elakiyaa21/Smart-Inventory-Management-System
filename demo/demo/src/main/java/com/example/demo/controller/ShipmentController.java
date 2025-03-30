package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.example.demo.entity.Shipment;
import com.example.demo.service.ShipmentService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {
    @Autowired
    ShipmentService serv;
    @PostMapping
    public ResponseEntity<?> addShipment(@Valid @RequestBody Shipment shipment) {
        // Check if shipment date is before estimated delivery date
        if (!serv.isValidShipmentDate(shipment)) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Shipment date must be before the estimated delivery date.");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        Shipment savedShipment = serv.createShipment(shipment);
        return ResponseEntity.ok(savedShipment);
    }
    @GetMapping
    public List<Shipment>getUser()
    {
        return serv.getAllShipment();
    }
    @GetMapping("/{id}")
    public Optional<Shipment>getById(@PathVariable Long id)
    {
        return serv.getById(id);
    }
    @PutMapping("/{id}")
    public Shipment UpdateShipment(@PathVariable Long id,@RequestBody Shipment u)
    {
        return serv.UpdateShipment(id,u);
    }
    @DeleteMapping("/{id}")
    public String deleteShipment(@PathVariable Long id)
    {
        return serv.deleteById(id);
    }
    @GetMapping("/page")
    public Page<Shipment>getPage(@RequestParam (defaultValue = "0") int page,@RequestParam(defaultValue = "10")int size)
    {
        return serv.getShipmentByPage(page, size);
    }
    @GetMapping("/sort")
    public List<Shipment>sortByShipment()
    {
        return serv.sortByShipment();
    }
    @GetMapping("/custom/{cost}")
    public List<Shipment> findByCost(@PathVariable double cost)
    {
        return serv.getByCost(cost);
    }
    @GetMapping("/jpql/{status}")
    public List<Shipment>getBystatus(@PathVariable String status)
    {
        return serv.getBystatus(status);
    }
}
package com.example.demo.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class Shipment {
    @Id
    private Long id;
    private String origin;
    private String destination;
    private Date shipmentDate;
    private Date estimatedDeliveryDate;
    private String status;
    private Double weight;
    private Double volume;
    private Double cost;

    
// @OneToMany(mappedBy = "shipment")
// @JsonIgnore
// private List<Delivery> deliveries;
@OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Delivery> deliveries;


    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    
    public Date getShipmentDate() { return shipmentDate; }
    public void setShipmentDate(Date shipmentDate) { this.shipmentDate = shipmentDate; }
    
    public Date getEstimatedDeliveryDate() { return estimatedDeliveryDate; }
    public void setEstimatedDeliveryDate(Date estimatedDeliveryDate) { this.estimatedDeliveryDate = estimatedDeliveryDate; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }
    
    public Double getVolume() { return volume; }
    public void setVolume(Double volume) { this.volume = volume; }
    
    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }

    public List<Delivery> getDeliveries() { return deliveries; }
    public void setDeliveries(List<Delivery> deliveries) { this.deliveries = deliveries; }
}
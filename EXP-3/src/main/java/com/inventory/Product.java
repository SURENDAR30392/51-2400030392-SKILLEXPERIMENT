package com.inventory;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    
    @Column(name = "cost")
    private Double price; // Use Double wrapper to handle NULLs from DB
    
    private Integer quantity; // Use Integer wrapper for safety as well

    public Product() {}

    public Product(String name, String description, Double price, Integer quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    // Update your Getters and Setters to use Double/Integer as well
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %-10s | Price: %s | Qty: %s", 
            id, name, (price != null ? price : "0.0"), (quantity != null ? quantity : "0"));
    }
}
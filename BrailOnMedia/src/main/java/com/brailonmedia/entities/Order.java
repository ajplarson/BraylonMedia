package com.brailonmedia.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author jackelder
 */
@Entity
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customerId;
    
    @Column(nullable = false)
    private LocalDateTime orderDate;
    
    @Column(nullable = false)
    private LocalDateTime fulfillmentDate;
    
    @Column(nullable = false)
    private String status;
    
    @Column(nullable = false)
    private BigDecimal price;
    
    @ManyToMany 
    @JoinTable(name = "orderProduct",
            joinColumns = {
                @JoinColumn(name = "orderId")},
            inverseJoinColumns = {
                @JoinColumn(name = "productId")})
    private List<Product> products;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getFulfillmentDate() {
        return fulfillmentDate;
    }

    public void setFulfillmentDate(LocalDateTime fulfillmentDate) {
        this.fulfillmentDate = fulfillmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    
   
}
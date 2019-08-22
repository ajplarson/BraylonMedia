package com.crudApp20.demo.entities;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author jackelder
 */
@Entity
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    
    @Column(nullable = false)
    private String customerFirstName;
    
    @Column (nullable = false)
    private String customerLastName;
    
    @Column (nullable = false)
    private String companyName;
    
    @Column (nullable = false)
    private String phone;
    
    @Column (nullable = false)
    private String email;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.customerId);
        hash = 97 * hash + Objects.hashCode(this.customerFirstName);
        hash = 97 * hash + Objects.hashCode(this.customerLastName);
        hash = 97 * hash + Objects.hashCode(this.companyName);
        hash = 97 * hash + Objects.hashCode(this.phone);
        hash = 97 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.customerFirstName, other.customerFirstName)) {
            return false;
        }
        if (!Objects.equals(this.customerLastName, other.customerLastName)) {
            return false;
        }
        if (!Objects.equals(this.companyName, other.companyName)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.customerId, other.customerId)) {
            return false;
        }
        return true;
    }
    
    
    
    
}

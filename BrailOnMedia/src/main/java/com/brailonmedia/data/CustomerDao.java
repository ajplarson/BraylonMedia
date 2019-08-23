package com.brailonmedia.data;

import com.brailonmedia.entities.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jackelder
 */
@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {
    
    @Query(value = "SELECT c.*, s.userId FROM User u JOIN SalesVisit s ON s.customerId = u.userId JOIN Customer c ON s.customerID = c.customerID WHERE u.username = ?1",
            nativeQuery = true)
    public List<Customer> findAllCustomersByUser(String username);
    
}

package com.crudApp20.demo.data;

import com.crudApp20.demo.entities.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jackelder
 */
public interface OrderDao extends JpaRepository<Order, Integer> {
    
    public List<Order> findByStatus(String status);
    
}

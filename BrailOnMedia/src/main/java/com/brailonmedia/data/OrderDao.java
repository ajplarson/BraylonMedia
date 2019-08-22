package com.brailonmedia.data;

import com.brailonmedia.entities.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jackelder
 */
@Repository
public interface OrderDao extends JpaRepository<Order, Integer> {
    @Query(value = "SELECT * from `Order` o where `status` like ?1",nativeQuery=true)
    public List<Order> findAllByStatus(String status);
    
}

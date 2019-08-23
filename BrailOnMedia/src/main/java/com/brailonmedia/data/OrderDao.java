package com.brailonmedia.data;

import com.brailonmedia.entities.Order;
import java.time.LocalDateTime;
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
    
    @Query(value = "SELECT * FROM `Order` o WHERE `status` LIKE ?1 AND userId = ?2", nativeQuery=true)
    public List<Order> findAllByStatusAndUserId(String status, Integer userId);
    
    @Query(value = "SELECT * FROM `Order` o WHERE `status` LIKE \"completed\" AND fulfillmentDate > ?1")
    public List<Order> findAllCompletedDateLimited(LocalDateTime dateCutoff);
    
}

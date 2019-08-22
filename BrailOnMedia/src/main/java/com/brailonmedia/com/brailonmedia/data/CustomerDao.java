package com.brailonmedia.com.brailonmedia.data;

import com.brailonmedia.com.brailonmedia.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jackelder
 */
@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {
    
}

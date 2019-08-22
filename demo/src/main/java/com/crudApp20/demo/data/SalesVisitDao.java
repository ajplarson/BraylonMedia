package com.crudApp20.demo.data;

import com.crudApp20.demo.entities.SalesVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jackelder
 */

@Repository
public interface SalesVisitDao extends JpaRepository<SalesVisit, Integer> {
    
}

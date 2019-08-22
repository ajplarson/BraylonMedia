package com.crudApp20.demo.data;

import com.crudApp20.demo.entities.SalesVisit;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jackelder
 */
@Repository
public interface SalesVisitDao extends JpaRepository<SalesVisit, Integer> {
    
    @Query(
        value="SELECT * FROM SalesVisit WHERE date >= ?1",
        nativeQuery = true)
    public List<SalesVisit> findSalesVisitsAfter(LocalDate cutoffDate);
    
}

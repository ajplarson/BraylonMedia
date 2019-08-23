package com.brailonmedia.data;

import com.brailonmedia.entities.SalesVisit;
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
        value="SELECT * FROM SalesVisit WHERE visitDate >= ?1",
        nativeQuery = true)
    public List<SalesVisit> findSalesVisitsAfter(LocalDate cutoffDate);
    
    @Query(value="SELECT s.* FROM Salesvisit s JOIN user u ON s.userId = u.userId WHERE u.username = ?1 AND s.visitDate >= ?2",
            nativeQuery=true)
    public List<SalesVisit> findSalesVisitsByUserAfter(String username, LocalDate cutoffDate);
    
}

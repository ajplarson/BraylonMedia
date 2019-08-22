package com.crudApp20.demo.data;

import com.crudApp20.demo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jackelder
 */
public interface RoleDao extends JpaRepository<Role, Integer> {
    
}

package com.crudApp20.demo.data;

import com.crudApp20.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jackelder
 */
public interface UserDao extends JpaRepository<User, Integer> {
    
}

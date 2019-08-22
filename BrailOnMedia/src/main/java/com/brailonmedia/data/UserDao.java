package com.brailonmedia.data;

import com.brailonmedia.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jackelder
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    
    public User findByEmail(String email);
    
}

package com.brailonmedia.data;

import com.brailonmedia.entities.Role;
import com.brailonmedia.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jackelder
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {
//    @Query(value="Select u from User u where username like ?1",nativeQuery=true)

    public User findUserByUsername(String username);

    @Query(value = "Select u from User u where username like ?1", nativeQuery = true)
    public User findUserUsername(String username);
    
    @Query(value = "SELECT COUNT(u.*) FROM user u JOIN userrole ur ON u.userId = ur.userId JOIN role r ON ur.roleId = r.roleId WHERE r.role LIKE 'salesrep'",
            nativeQuery = true)
    public Integer findSalesRepCount();
    
    @Query(value = "SELECT r.* FROM user u JOIN userrole ur ON u.userId = ur.userId JOIN role ON ur.roleId = r.roleId WHERE u.username LIKE ?1",
            nativeQuery = true)
    public Role findRoleByUsername(String username);
    
}

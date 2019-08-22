package com.brailonmedia.service;

import com.brailonmedia.data.RoleDao;
import com.brailonmedia.data.UserDao;
import com.brailonmedia.entities.Role;
import com.brailonmedia.entities.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author jackelder
 */
@Service
public class UserManager {
    
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder encoder;
    
    @Autowired
    public UserManager(UserDao userDao, RoleDao roleDao, PasswordEncoder encoder){
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.encoder = encoder;
    }
    
    public List<User> findAllUsers(){
        return userDao.findAll();
    }
    
    public Role getRoleByRole(String role){
        return roleDao.findRoleByRole(role);
    }
    
    public User createAccount(User user){
        if(userDao.findByEmail(user.getEmail()) != null){
            user.setUserId(-1);
            return user;
        }
        return userDao.save(user);
    }
    
    public void deleteUser(Integer id){
        userDao.deleteById(id);
    }
    
    public String encode(String password){
        return encoder.encode(password);
    }
    
    public User getAccountByEmail(String email){
        return userDao.findByEmail(email);
    }
    
}

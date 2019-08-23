package com.brailonmedia.controllers;

import com.brailonmedia.data.UserDao;
import com.brailonmedia.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author vince
 */
@Controller
public class LoginController {

    private UserManager userManager;
    private UserDao userDao;

    @Autowired
    public LoginController(UserManager userManager, UserDao userDao) {
        this.userManager = userManager;
        this.userDao = userDao;
    }

    @GetMapping("/login")
    public String displayLogin() {
        return "login";
    }

    @GetMapping("/")
    public String displayLanding() {
        return "index";
    }
    
    @GetMapping("/homeGet")
    public String getHome() {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (userDao.findRoleByUsername(currentUserName).equals("SalesRep")) {
            return "redirect:/salesHome";
        }
        return "redirect:/salesHome";
    }
}

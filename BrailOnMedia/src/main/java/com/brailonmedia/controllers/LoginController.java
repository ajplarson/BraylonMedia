package com.brailonmedia.controllers;

import com.brailonmedia.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author vince
 */
@Controller
public class LoginController {
    
    private UserManager userManager;
    
    @Autowired
    public LoginController(UserManager userManager){
        this.userManager = userManager;
    }

    @GetMapping("/login")
    public String displayLogin() {
        return "login";
    }

//    @GetMapping("/")
//    public String adsgsijd() {
//        return "salesHome";
//    }

}
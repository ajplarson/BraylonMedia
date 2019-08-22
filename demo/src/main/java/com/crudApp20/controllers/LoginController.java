package com.crudApp20.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author jackelder
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String displayLogin() {
        return "login";
    }

    @GetMapping("/")
    public String adsgsijd() {
        return "salesHome";
    }

}

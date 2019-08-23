/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brailonmedia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author vince
 */
@Controller
public class LeaderController {
    @GetMapping("/leaderHome")
    public String getLeaderHome(){
        return "leaderHome";
    }
}

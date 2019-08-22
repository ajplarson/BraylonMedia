/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crudApp20.controllers;

import com.crudApp20.demo.data.OrderDao;
import com.crudApp20.demo.data.SalesVisitDao;
import java.time.LocalDate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/*
*
*
*  Don't blame me if this is bad
 */
@RestController


public class SaleController {

    
    private OrderDao oDao;
    private SalesVisitDao sVisit;

    
    public SaleController(OrderDao oDao, SalesVisitDao sVisit) {
        this.oDao = oDao;
        this.sVisit = sVisit;
    }

    
    @GetMapping("/sales")
    public String displaySalesHome(Model model) {
        model.addAttribute("salesPending", oDao.findByStatus("pending"));
        model.addAttribute("visitsUpcoming", sVisit.findSalesVisitsAfter(LocalDate.now()));
     
        return "salesHome";
    }
}

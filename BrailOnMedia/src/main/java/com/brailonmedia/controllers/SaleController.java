/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brailonmedia.controllers;

import com.brailonmedia.data.CustomerDao;
import com.brailonmedia.data.OrderDao;
import com.brailonmedia.data.SalesVisitDao;
import com.brailonmedia.entities.Customer;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author vince
 */
@Controller
public class SaleController {

    
    private OrderDao oDao;
    private SalesVisitDao sVisit;
    private CustomerDao cDao;

    
    public SaleController(OrderDao oDao, SalesVisitDao sVisit, CustomerDao cDao) {
        this.oDao = oDao;
        this.sVisit = sVisit;
        this.cDao = cDao;
    }

    
    @GetMapping("/salesHome")
    public String displaySalesHome(Model model) {
        model.addAttribute("salesPending", oDao.findAllByStatus("pending"));
        model.addAttribute("visitsUpcoming", sVisit.findSalesVisitsAfter(LocalDate.now()));
        return "salesHome";
    }
    
    @GetMapping("/salesCustomers")
    public String displayCustomers(Model model) {
        List<Customer> customerList = cDao.findAll();
        model.addAttribute("customers",customerList);
        return "customers";
    }
    
    @GetMapping("/salesAdd")
    public String displayAddCustomer(Model model) {
        model.addAttribute("customers");
        return "customer-add";
    }
    
    @PostMapping("/salesAdd")
    public String addCustomer(@Valid Customer customer, BindingResult result) {
        cDao.save(customer);
        return "redirect:/salesCustomers";
    }
    
}

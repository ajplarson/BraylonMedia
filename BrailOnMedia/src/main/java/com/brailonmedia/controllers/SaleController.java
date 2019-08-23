/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brailonmedia.controllers;

import com.brailonmedia.data.CustomerDao;
import com.brailonmedia.data.OrderDao;
import com.brailonmedia.data.SalesVisitDao;
import com.brailonmedia.data.UserDao;
import com.brailonmedia.entities.Customer;
import com.brailonmedia.entities.Order;
import com.brailonmedia.entities.SalesVisit;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private UserDao uDao;

    public SaleController(OrderDao oDao, SalesVisitDao sVisit, CustomerDao cDao, UserDao udao) {
        this.oDao = oDao;
        this.sVisit = sVisit;
        this.cDao = cDao;
        this.uDao = uDao;
    }

    @GetMapping("/sales")
    public String displaySalesSalesVists(Model model) {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        List<SalesVisit> salesList = sVisit.findSalesByUSername(currentUserName);
        model.addAttribute("visits", salesList);
        return "salesVisit";
    }

    @GetMapping("/salesHome")
    public String displaySalesHome(Model model) {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Order> orderList = oDao.findAllByStatusAndUserName("pending", currentUserName);
        List<SalesVisit> salesList = sVisit.findSalesVisitsByUserAfter(currentUserName, LocalDate.now());
        model.addAttribute("salesPending", orderList);
        model.addAttribute("visitsUpcoming", salesList);
        return "salesHome";
    }

    @GetMapping("/salesCustomers")
    public String displayCustomers(Model model) {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Customer> customerList = cDao.findAllCustomersByUser(currentUserName);
        model.addAttribute("customers", customerList);
        return "customers";
    }

    @GetMapping("/salesAdd")
    public String displayAddCustomer(Model model) {
        model.addAttribute("customers");
        return "customer-add";
    }

    @GetMapping("/calendar")
    public String displayCalendar() {
        return "calendar";
    }

    @PostMapping("/salesAdd")
    public String addCustomer(@Valid Customer customer, BindingResult result) {
        cDao.save(customer);
        return "redirect:/salesCustomers";
    }
}

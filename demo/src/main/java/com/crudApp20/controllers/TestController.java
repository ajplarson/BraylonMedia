/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crudApp20.controllers;

import com.crudApp20.demo.data.OrderDao;
import com.crudApp20.demo.entities.Order;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vince
 */
@RestController
public class TestController {
    
    private OrderDao orderDao;
    
    public TestController(OrderDao orderDao){
        this.orderDao=orderDao;
    }
    @GetMapping("/hom")
    public List<Order> orderList(){
        return orderDao.findAll();
    }
}

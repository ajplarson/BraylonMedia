package com.brailonmedia.controllers;

import com.brailonmedia.data.CustomerDao;
import com.brailonmedia.data.OrderDao;
import com.brailonmedia.data.UserDao;
import com.brailonmedia.entities.Order;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    private final OrderDao orderDao;
    private final UserDao userDao;
    private final CustomerDao customerDao;

    @Autowired
    public OrderController(OrderDao orderDao, UserDao userDao, CustomerDao customerDao) {
        this.orderDao = orderDao;
        this.userDao = userDao;
        this.customerDao = customerDao;
    }

    @GetMapping("/orders/all")
    public String allOrders(Model model) {
        model.addAttribute("orders", orderDao.findAll());
        model.addAttribute("users", userDao.findAll());
        model.addAttribute("customers", customerDao.findAll());

        return "orders";
    }

    @GetMapping("/orders/add/{customerId}")
    public String addOrderToCustomer(@PathVariable int customerId, Order order, Model model) {
        model.addAttribute("users", userDao.findAll());
        model.addAttribute("customers", customerDao.findAll());

        return "order-add";
    }

    @PostMapping("/orders/add/{customerId}")
    public String addAssignment(@PathVariable int customerId, @Valid Order order,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("users", userDao.findAll());
            model.addAttribute("customers", customerDao.findAll());
            return "order-add";
        }

        orderDao.save(order);

        return "redirect:/";
    }

    @GetMapping("/orders/edit/{id}")
    public String editOrder(@PathVariable int id, Model model) {
        Optional<Order> o = orderDao.findById(id);

        if (o == null) {
            return "redirect:/orders";
        }
        model.addAttribute("order", o);
        model.addAttribute("users", userDao.findAll());
        model.addAttribute("customers", customerDao.findAll());

        return "order-edit";
    }

    @PostMapping("/orders/edit/{id}")
    public String edit(@Valid Order o,
            BindingResult result,
            int[] dexNumbers,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("users", userDao.findAll());
            model.addAttribute("customers", customerDao.findAll());
            return "order-edit";
        }
        
        orderDao.save(o);
        
        return "redirect:/orders";
    }
}
package com.brailonmedia.controllers;

import com.brailonmedia.data.CustomerDao;
import com.brailonmedia.data.OrderDao;
import com.brailonmedia.data.UserDao;
import com.brailonmedia.entities.Order;
import com.brailonmedia.entities.User;
import com.brailonmedia.service.UserManager;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
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

    private final String PENDING = "pending";
    private final String COMPLETED = "completed";
    private final String ARCHIVED = "archived";

    private final OrderDao orderDao;
    private final UserDao userDao;
    private final CustomerDao customerDao;
    private final UserManager userManager;

    @Autowired
    public OrderController(OrderDao orderDao, UserDao userDao, CustomerDao customerDao, UserManager userManager) {
        this.orderDao = orderDao;
        this.userDao = userDao;
        this.customerDao = customerDao;
        this.userManager = userManager;
    }

    @GetMapping("/orders/all")
    public String allOrders(Model model, Principal p) {
        model.addAttribute("salesPending", getAllPendingOrdersByUser(p.getName()));
        model.addAttribute("salesArchived", getAllArchivedOrdersByUser(p.getName()));
        model.addAttribute("salesCompleted", getAllCompletedOrdersByUser(p.getName()));
        return "orders";
    }

    @GetMapping("/orders/add/")
    public String addOrderToCustomer(Order order, Model model) {
        model.addAttribute("users", userDao.findAll());
        model.addAttribute("customers", customerDao.findAll());

        return "order-add";
    }

    @PostMapping("/orders/add/")
    public String addAssignment(@Valid Order order,
            BindingResult result, Model model, Principal p) {

        if (result.hasErrors()) {
            model.addAttribute("users", userDao.findAll());
            model.addAttribute("customers", customerDao.findAll());
            return "order-add";
        }
        
        order = orderDao.save(order);
        
        User user = userManager.getUserByUsername(p.getName());
        List<Order> orders = user.getOrders();
        orders.add(order);
        user.setOrders(orders);
        
        user = userDao.save(user);

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

    //Basically, this is the service layer
    private String currentUserNameSimple(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return principal.getName();
    }

    private List<Order> getAllPendingOrdersByUser(String username) {
        return orderDao.findAllByStatusAndUserName(PENDING, username);
    }

    private List<Order> getAllCompletedOrdersByUser(String username) {
        return orderDao.findAllByStatusAndUserName(COMPLETED, username);
    }

    private List<Order> getAllArchivedOrdersByUser(String username) {
        return orderDao.findAllByStatusAndUserName(ARCHIVED, username);
    }

    private List<Order> getAllPendingOrders() {
        return orderDao.findAllByStatus(PENDING);
    }

    private int getTotalNumberOfPendingOrders() {
        return getAllPendingOrders().size();
    }

    private List<Order> getAllCompletedOrders() {
        return orderDao.findAllByStatus(COMPLETED);
    }

    private List<Order> getAllArchivedOrders() {
        return orderDao.findAllByStatus(ARCHIVED);
    }

    private List<Order> getAllCompletedOrdersInPastWeek() {
        return orderDao.findAllCompletedDateLimited(LocalDateTime.now().minusDays(7));
    }

    private int getNumberOfCompletedOrdersInPastWeek() {
        return getAllCompletedOrdersInPastWeek().size();
    }

    private int getTotalNumberOfSalesReps() {
        return userDao.findSalesRepCount();
    }

    private BigDecimal getTotalRevenueOfCompletedOrdersInPastWeek() {
        BigDecimal total = BigDecimal.ZERO;
        for (Order order : getAllCompletedOrdersInPastWeek()) {
            total.add(order.getPrice());
        }
        return total;
    }

    private BigDecimal getTotalRevenueForPendingOrders() {
        BigDecimal total = BigDecimal.ZERO;
        for (Order order : getAllPendingOrders()) {
            total.add(order.getPrice());
        }
        return total;
    }
    
    private List<User> getAllSalesReps(){
        return userDao.findAllSalesReps();
    }
}

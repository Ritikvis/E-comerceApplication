package com.e_commerce.Application.Controller;

import com.e_commerce.Application.Entity.Order;
import com.e_commerce.Application.Entity.User;
import com.e_commerce.Application.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("User")
public class UserController {
    @Autowired
    private UserService userService;
// first add the user then product then order

//  http://localhost:8080/User/addUserWithOrder?orderId=4
    @PostMapping("/addUserWithOrder")
    public ResponseEntity<User> addUserWithOrder(
            @RequestBody User user,
            @RequestParam(required = false) Long orderId) {

        User savedUser = userService.addUserWithOrder(user, orderId);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
//  http://localhost:8080/User/1/orders
    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<Order>> getAllOrdersForUser(@PathVariable Long userId) {
        List<Order> orders = userService.getAllOrdersByUserId(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

}

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
    @PostMapping("addUser")
    public ResponseEntity<User> AddUser(@RequestBody User user){
        User user1 = userService.AddUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }
    @PostMapping("addOrderToUser")
    public ResponseEntity<Void>  addOrderToUser(@RequestParam Long userId,@RequestParam Long orderId){
        List<Order> orders = userService.addOrderToUser(userId,orderId);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<Order>> getAllOrdersForUser(@PathVariable Long userId) {
        List<Order> orders = userService.getAllOrdersByUserId(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

}

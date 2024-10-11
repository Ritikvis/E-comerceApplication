package com.e_commerce.Application.Controller;

import com.e_commerce.Application.Entity.Order;
import com.e_commerce.Application.Entity.Product;
import com.e_commerce.Application.Enum.PaymentMode;
import com.e_commerce.Application.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("addOrder")
    public ResponseEntity<Order> createOrderForAProducts(
            @RequestParam Long userId,
            @RequestParam PaymentMode paymentMode) {
        Order order = orderService.createOrderForProductsStartingWithA(userId, paymentMode);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
    @PostMapping("addProductToOrder")
    public ResponseEntity<Void> AddProductToOrder(@RequestParam Long orderId,@RequestParam Long productId){
        orderService.AddProductToOrder(orderId,productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

package com.e_commerce.Application.Service;

import com.e_commerce.Application.Entity.Order;
import com.e_commerce.Application.Entity.User;
import com.e_commerce.Application.Repository.OrderRepository;
import com.e_commerce.Application.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    public User AddUser(User user) {
        return userRepository.save(user);
    }

    public List<Order> addOrderToUser(Long userId, Long orderId) {
        Order orders  = orderRepository.findById(orderId)
                .orElseThrow(()->new RuntimeException("Id not found :"+orderId));
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("Id not found : "+ userId));
        user.getOrders().add(orders);
        userRepository.save(user);
        return user.getOrders();

    }


    public List<Order> getAllOrdersByUserId(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User with ID: " + userId + " not found"));

        return user.getOrders();
    }
}

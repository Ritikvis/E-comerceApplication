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

    public User addUserWithOrder(User user, Long orderId) {
        // Save or update the user
        User savedUser = userRepository.save(user);

        // If an order ID is provided, link the order to the user
        if (orderId != null) {
            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + orderId));

            List<Order> orders = savedUser.getOrders();
            if (orders == null) {
                orders = new ArrayList<>();
                savedUser.setOrders(orders);
            }

            orders.add(order);
            userRepository.save(savedUser); // Save the updated user with the linked order
        }

        return savedUser;
    }





    public List<Order> getAllOrdersByUserId(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User with ID: " + userId + " not found"));

        return user.getOrders();
    }
}

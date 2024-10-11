package com.e_commerce.Application.Service;

import com.e_commerce.Application.Entity.Order;
import com.e_commerce.Application.Entity.Product;
import com.e_commerce.Application.Entity.User;
import com.e_commerce.Application.Enum.PaymentMode;
import com.e_commerce.Application.Repository.OrderRepository;
import com.e_commerce.Application.Repository.ProductRepository;
import com.e_commerce.Application.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;


    public Order createOrderForProductsStartingWithA(Long userId, PaymentMode paymentMode) {
        List<Product> products = productRepository.findByProductNameStartingWith("E");
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("Id not found : "+ userId));
        Order order = new Order();
        order.setUser(user);
        order.setProducts(products);
        order.setPaymentMode(paymentMode);
        return orderRepository.save(order);
    }

    public void AddProductToOrder(Long orderId, Long productId) {
        Order orders  = orderRepository.findById(orderId)
                .orElseThrow(()->new RuntimeException("Id not found :"+orderId));
        Product product  =  productRepository.findById(productId)
                .orElseThrow(()->new RuntimeException("Id not found : "+ productId));
        orders.getProducts().add(product);
        orderRepository.save(orders);
    }
}

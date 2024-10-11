package com.e_commerce.Application.Service;

import com.e_commerce.Application.Entity.Order;
import com.e_commerce.Application.Entity.Product;
import com.e_commerce.Application.Repository.OrderRepository;
import com.e_commerce.Application.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    public Product AddProducts(Product product) {
        return productRepository.save(product);
    }

    public Product getMaxPricedProductForCategory(String category) {
        List<Product> products = productRepository.findByCategory(category);

        if (products.isEmpty()) {
           return null;
        }

        // Initialize maxProduct to the first product
        Product maxProduct = products.get(0);
        for(Product product : products){
            if(product.getProductPrice() > maxProduct.getProductPrice()){
                maxProduct = product;
            }
        }
        return maxProduct;
    }

    public Product findMostOrderedProduct() {
        // Fetch all orders
        List<Order> orders = orderRepository.findAll();

        // Variables to track the most ordered product and its count
        Product mostOrderedProduct = null;
        int maxCount = 0;

        // Iterate through each order
        for (Order order : orders) {
            // For each product in the order, check and update counts
            for (Product product : order.getProducts()) {
                int currentCount = 1; // Start count for this product

                // Check if this product is already the most ordered
                if (mostOrderedProduct != null && mostOrderedProduct.equals(product)) {
                    currentCount++; // Increment if it's the same product
                }

                // Update the most ordered product if necessary
                if (currentCount > maxCount || (currentCount == maxCount && product.getProductPrice() > mostOrderedProduct.getProductPrice())) {
                    mostOrderedProduct = product;
                    maxCount = currentCount;
                }
            }
        }
        return mostOrderedProduct;
    }
}

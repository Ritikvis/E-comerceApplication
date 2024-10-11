package com.e_commerce.Application.Controller;

import com.e_commerce.Application.Entity.Product;
import com.e_commerce.Application.Entity.User;
import com.e_commerce.Application.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("addProduct")
    public ResponseEntity<Product> AddProduct(@RequestBody Product product){
        Product product1 = productService.AddProducts(product);
        return new ResponseEntity<>(product1,HttpStatus.CREATED);
    }
    @GetMapping("max-price")
    public ResponseEntity<Product> getMaxPricedProduct(@RequestParam String category) {
        System.out.println("Fetching max-priced product for category: " + category);
        Product product = productService.getMaxPricedProductForCategory(category);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @GetMapping("/most-ordered")
    public ResponseEntity<Product> getMostOrderedProduct() {
        Product product = productService.findMostOrderedProduct();

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // No products found
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
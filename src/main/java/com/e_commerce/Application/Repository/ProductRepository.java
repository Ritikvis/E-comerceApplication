package com.e_commerce.Application.Repository;

import com.e_commerce.Application.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByProductNameStartingWith(String prefix);
    List<Product> findByCategory(String category);
}

package com.e_commerce.Application.Entity;

import com.e_commerce.Application.Enum.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    @Enumerated(EnumType.STRING)
    private Category productCategory;

    private Double productPrice;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

}

package com.e_commerce.Application.Repository;

import com.e_commerce.Application.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}

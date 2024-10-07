package com.regain.adminzn.repository;

import com.regain.adminzn.model.Orders;
import com.regain.adminzn.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    boolean existsByProduct(Product product);
    Orders findByProductId(int productId);
}

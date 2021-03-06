package com.edureka.orderms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edureka.orderms.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}

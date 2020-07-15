package com.edureka.orderms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edureka.orderms.model.Order;

/**
 * https://www.javabullets.com/spring-data-rest-projections/
 * https://stackoverflow.com/questions/45401734/how-to-work-with-dto-in-spring-data-rest-projects
 *
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

}

package com.nandini.customerordermanagement.Repository;

import com.nandini.customerordermanagement.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}

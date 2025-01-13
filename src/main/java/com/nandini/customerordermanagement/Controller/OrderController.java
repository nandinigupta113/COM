package com.nandini.customerordermanagement.Controller;

import com.nandini.customerordermanagement.Model.Order;
import com.nandini.customerordermanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<Order> addOrder(@RequestBody Order order, @PathVariable int customerId) {
        try {
            Order savedOrder = orderService.addOrder(customerId, order);
            return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{customerId}/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int customerId, @PathVariable int orderId) {
        boolean isDeleted = orderService.deleteOrder(customerId, orderId);
        return isDeleted ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{customerId}/{orderId}")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order, @PathVariable int customerId, @PathVariable int orderId) {
        Optional<Order> updatedOrder = orderService.updateOrder(customerId, orderId, order);
        return updatedOrder.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}

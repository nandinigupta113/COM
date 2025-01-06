package com.nandini.customerordermanagement.Controller;
import com.nandini.customerordermanagement.Model.Customer;
import com.nandini.customerordermanagement.Model.Order;
import com.nandini.customerordermanagement.Repository.CustomerRepository;
import com.nandini.customerordermanagement.Repository.OrderRepository;
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
    OrderRepository orderRepo;

    @Autowired
    CustomerRepository customerRepo;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/{customerId}")
    public ResponseEntity<Order> addOrder(@RequestBody Order order, @PathVariable int customerId) {
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        order.setCustomer(customer);
        Order savedOrder = orderRepo.save(order);
        customer.getOrders().add(savedOrder);
        customerRepo.save(customer);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }


    @DeleteMapping("/{customerId}/{orderid}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int customerId, @PathVariable int orderid) {
        Optional<Customer> customerOptional = customerRepo.findById(customerId);

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            Optional<Order> orderOptional = orderRepo.findById(orderid);

            if (orderOptional.isPresent() && orderOptional.get().getCustomer().getCustomerId() == customerId) {
                orderRepo.delete(orderOptional.get());
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{customerId}/{orderId}")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order, @PathVariable int customerId, @PathVariable int orderId) {
        Optional<Customer> customerOptional = customerRepo.findById(customerId);

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            Optional<Order> orderOptional = orderRepo.findById(orderId);

            if (orderOptional.isPresent() && orderOptional.get().getCustomer().getCustomerId() == customerId) {
                Order existingOrder = orderOptional.get();
                existingOrder.setCategory(order.getCategory());
                existingOrder.setProduct(order.getProduct());
                existingOrder.setQuantity(order.getQuantity());
                existingOrder.setPrice(order.getPrice());
                existingOrder.setTotal(order.getTotal());
                existingOrder.setStatus(order.getStatus());
                orderRepo.save(existingOrder);
                return ResponseEntity.ok(existingOrder);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

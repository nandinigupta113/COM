package com.nandini.customerordermanagement.service;

import com.nandini.customerordermanagement.Model.Customer;
import com.nandini.customerordermanagement.Model.Order;
import com.nandini.customerordermanagement.Repository.CustomerRepository;
import com.nandini.customerordermanagement.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private CustomerRepository customerRepo;

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public Order addOrder(int customerId, Order order) {
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        order.setCustomer(customer);
        Order savedOrder = orderRepo.save(order);
        customer.getOrders().add(savedOrder);
        customerRepo.save(customer);
        return savedOrder;
    }

    public boolean deleteOrder(int customerId, int orderId) {
        Optional<Customer> customerOptional = customerRepo.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            Optional<Order> orderOptional = orderRepo.findById(orderId);
            if (orderOptional.isPresent() && orderOptional.get().getCustomer().getCustomerId() == customerId) {
                orderRepo.delete(orderOptional.get());
                return true;
            }
        }
        return false;
    }

    public Optional<Order> updateOrder(int customerId, int orderId, Order order) {
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
                return Optional.of(existingOrder);
            }
        }
        return Optional.empty();
    }
}

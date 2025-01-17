package com.nandini.customerordermanagement.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderid;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customer_id", nullable = false)
    public Customer customer;

    @Column(name = "Category")
    private String category;

    @Column(name = "Product")
    private String product;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "Price")
    private double price;

    @Column(name = "total")
    private double total;

    @Column(name = "STATUS")
    private String status;

    public Order(String category, String product, int quantity, double price, double total, String status) {
        this.category = category;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.status = status;
    }

}

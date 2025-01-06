package com.nandini.customerordermanagement.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    public Order() {

    }
    public Order(String category, String product, int quantity, double price, double total) {
        this.category = category;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }


    public double getTotal(){
        return total;
    }
   public void setTotal(double total){
        this.total = total;
   }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "Order [orderid=" + orderid + ", customer=" + customer + ", category=" + category
                + ", product=" + product + ", quantity=" + quantity + ", price=" + price + ", total=" + total + "]";
    }
}

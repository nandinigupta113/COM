package com.nandini.customerordermanagement.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @Column(name = "Customer_Name")
    private String CustomerName;

    @Column(name = "Email_Id")
    private String EmailId;

    @Column(name = "Contact_No")
    private long ContactNo;

    @Column(name = "Payment_Method")
    private String PaymentMethod;

@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,fetch =FetchType.LAZY )
@JsonManagedReference
private List<Order> orders = new ArrayList<>();


    public Customer(long contactNo, String customerName, String emailId, String paymentMethod) {
        ContactNo = contactNo;
        CustomerName = customerName;
        EmailId = emailId;
        PaymentMethod = paymentMethod;
    }

    public Customer() {

    }
    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }



    public void setCustomerId(int id) {
        this.customerId = id;
    }

    public int getCustomerId() {
        return customerId;
    }


    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }



    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public long getContactNo() {
        return ContactNo;
    }

    public void setContactNo(long contactNo) {
        ContactNo = contactNo;
    }

    @Override
    public String toString() {
        return "Customer [customerId=" + customerId + ", CustomerName=" + CustomerName +
                ", EmailId=" + EmailId + ", ContactNo=" + ContactNo +
                ", PaymentMethod=" + PaymentMethod + "]";
    }
}

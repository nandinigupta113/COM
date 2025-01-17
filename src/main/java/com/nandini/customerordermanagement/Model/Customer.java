package com.nandini.customerordermanagement.Model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
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

}

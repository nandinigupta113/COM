package com.nandini.customerordermanagement.restapi;

import jakarta.persistence.*;

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
    private int ContactNo;

    @Column(name = "Payment_Method")
    private String PaymentMethod;

    public Customer(int contactNo, String customerName, String emailId, String paymentMethod) {
        ContactNo = contactNo;
        CustomerName = customerName;
        EmailId = emailId;
        PaymentMethod = paymentMethod;
    }

    public Customer() {

    }

    public void setId(int id) {
        this.customerId = id;
    }

    public int getId() {
        return customerId;
    }


    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }
    public String getEmailId() {
        return EmailId;
    }
    public void setEmailId(String emailId) {
        EmailId = emailId;
    }
    public int getContactNo() {
        return ContactNo;
    }
    public void setContactNo(int contactNo) {
        ContactNo = contactNo;
    }
    public String getPaymentMethod() {
        return PaymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }
    @Override
    public String toString() {
        return "Customer [" + "customerId=" + customerId + ", CustomerName=" + CustomerName + ", EmailId=" + EmailId
                + ", ContactNo=" + ContactNo + ", PaymentMethod=" + PaymentMethod + "]";
    }
}

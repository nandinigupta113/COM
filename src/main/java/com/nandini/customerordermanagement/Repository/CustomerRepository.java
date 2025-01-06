package com.nandini.customerordermanagement.Repository;

import com.nandini.customerordermanagement.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}

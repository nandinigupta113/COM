package com.nandini.customerordermanagement.Repository;

import com.nandini.customerordermanagement.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Customer, Integer>{

}

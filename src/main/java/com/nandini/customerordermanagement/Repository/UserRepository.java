package com.nandini.customerordermanagement.Repository;
import com.nandini.customerordermanagement.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

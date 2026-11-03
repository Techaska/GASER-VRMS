
package com.gb.gaser.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

    List<Customer> findByFullNameContainingIgnoreCase(String fullName);

    List<Customer> findByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);
}

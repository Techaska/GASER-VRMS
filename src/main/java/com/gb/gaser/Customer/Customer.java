package com.gb.gaser.Customer;

import com.gb.gaser.Vehicle.Vehicle;
import com.gb.gaser.custgarg.CustomerGarage;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Customer name is required")
    @Size(min = 2, max = 50,
            message = "Name must be 2 to 50 characters")
    @Column(nullable = false, length = 100)
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email address")
    @Size(max = 254)
    @Column(nullable = false, unique = true, length = 254)
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[6-9][0-9]{9}$",
            message = "Enter a valid 10-digit Indian mobile number")
    @Column(nullable = false, length = 10)
    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    private List<Vehicle> vehicles = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<CustomerGarage> garageAssociations = new ArrayList<>();
}
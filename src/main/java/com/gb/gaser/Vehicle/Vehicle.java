package com.gb.gaser.Vehicle;

import com.gb.gaser.Customer.Customer;
import com.gb.gaser.ServiceRecord.ServiceRecord;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Vehicle number is required")
    @Pattern(regexp = "^[A-Za-z0-9 -]{5,20}$",
            message = "Vehicle number must be 5 to 20 letters, digits, spaces or hyphens")
    @Column(nullable = false, unique = true, length = 20)
    private String vehicleNumber;

    @NotBlank(message = "Vehicle brand is required")
    @Size(max = 50, message = "Brand cannot exceed 50 characters")
    @Column(nullable = false, length = 50)
    private String brand;

    @NotBlank(message = "Vehicle model is required")
    @Size(max = 60, message = "Model cannot exceed 60 characters")
    @Column(nullable = false, length = 60)
    private String model;

    @NotBlank(message = "Fuel type is required")
    @Pattern(
            regexp = "(?i)^(petrol|diesel|electric|hybrid|cng|lpg|other)$",
            message = "Enter a supported fuel type"
    )
    @Column(nullable = false, length = 20)
    private String fuelType;

    @NotNull(message = "Purchase year is required")
    @Min(value = 1900, message = "Purchase year must be 1900 or later")
    @Max(value = 2100, message = "Enter a valid purchase year")
    @Column(nullable = false)
    private Integer purchaseYear;

    @NotNull(message = "Vehicle owner is required")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "vehicle")
    private List<ServiceRecord> serviceRecords = new ArrayList<>();
}
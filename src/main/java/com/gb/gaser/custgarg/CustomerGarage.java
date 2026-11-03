package com.gb.gaser.custgarg;

import com.gb.gaser.Customer.Customer;
import com.gb.gaser.Garage.Garage;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "customer_garages",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_customer_garage",
                columnNames = {"customer_id", "garage_id"}
        )
)
@Getter
@Setter
@NoArgsConstructor
public class CustomerGarage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Customer is required")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @NotNull(message = "Garage is required")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "garage_id", nullable = false)
    private Garage garage;

    @Column(nullable = false, updatable = false)
    private LocalDateTime associatedAt;

    @Column(nullable = false)
    private boolean active = true;

    @PrePersist
    protected void onCreate() {
        if (associatedAt == null) {
            associatedAt = LocalDateTime.now();
        }
    }
}
package com.gb.gaser.ServiceRecord;

import com.gb.gaser.Garage.Garage;
import com.gb.gaser.Vehicle.Vehicle;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "service_records")
@Getter
@Setter
@NoArgsConstructor
public class ServiceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Service date and time are required")
    @Column(nullable = false)
    private LocalDateTime serviceDateTime;

    @NotBlank(message = "Service type is required")
    @Size(max = 100, message = "Service type cannot exceed 100 characters")
    @Column(nullable = false, length = 100)
    private String serviceType;

    @NotNull(message = "Service cost is required")
    @DecimalMin(value = "0.0",
            message = "Service cost cannot be negative") @Column(nullable = false)
    private Double cost;

    @NotNull(message = "Odometer reading is required")
    @PositiveOrZero(message = "Odometer reading cannot be negative")
    @Column(nullable = false)
    private Integer odometerReading;

    @FutureOrPresent(message = "Next service date cannot be in the past")
    private LocalDate nextServiceDate;

    @Size(max = 1000, message = "Remarks cannot exceed 1000 characters")
    @Column(length = 1000)
    private String remarks;

    @NotNull(message = "Vehicle is required")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @NotNull(message = "Servicing garage is required")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "garage_id", nullable = false)
    private Garage garage;
}
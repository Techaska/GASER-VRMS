package com.gb.gaser.Garage;

import com.gb.gaser.ServiceRecord.ServiceRecord;
import com.gb.gaser.custgarg.CustomerGarage;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "garages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long garageId;

    @NotBlank(message = "Garage name is required")
    @Size(min = 2, max = 100,
            message = "Garage name must be 2 to 100 characters")
    @Column(nullable = false, length = 100)
    private String garageName;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[6-9][0-9]{9}$",
            message = "Enter a valid 10-digit Indian mobile number")
    @Column(nullable = false, length = 10)
    private String garagePhoneNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email address")
    @Size(max = 254)
    @Column(nullable = false, unique = true, length = 254)
    private String garageMail;

    @NotNull(message = "Opening time is required")
    @Column(nullable = false)
    private LocalTime openingTime;

    @NotNull(message = "Closing time is required")
    @Column(nullable = false)
    private LocalTime closingTime;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private boolean approved = false;

    @OneToMany(mappedBy = "garage")
    private List<ServiceRecord> serviceRecords = new ArrayList<>();

    @OneToMany(mappedBy = "garage")
    private List<CustomerGarage> customerAssociations = new ArrayList<>();

    @AssertTrue(message =
            "Opening time must be earlier than closing time")
    public boolean isOperatingHoursValid() {
        if (openingTime == null || closingTime == null) {
            return true;
        }
        return openingTime.isBefore(closingTime);
    }

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
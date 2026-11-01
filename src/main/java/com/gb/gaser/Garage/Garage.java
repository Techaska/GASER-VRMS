
package com.gb.gaser.Garage;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long garageId;

    @NotBlank(message = "Garage name is required")
    @Size(min = 2, max = 100,
            message = "Garage name must be 2 to 100 characters")
    @Column(nullable = false, length = 100)
    private String garageName;

    @Column(nullable = false, unique = true, length = 10)
    private String garageNumber;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[6-9][0-9]{9}$",
            message = "Enter a valid 10-digit Indian mobile number")
    @Column(nullable = false, length = 10)
    private String garagePhoneNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email address")
    @Size(max = 254, message = "Email cannot exceed 254 characters")
    @Column(nullable = false,unique = true, length = 254)
    private String garageMail;

    @NotNull(message = "Opening time is required")
    private LocalTime openingTime;

    @NotNull(message = "Closing time is required")
    private LocalTime closingTime;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    // Opening time must be earlier than closing time.
    // This version does not allow overnight opening hours.
    @AssertTrue(message =
            "Opening time must be earlier than closing time")
    public boolean isOperatingHoursValid() {
        if (openingTime == null || closingTime == null) {
            return true; // @NotNull reports missing values
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

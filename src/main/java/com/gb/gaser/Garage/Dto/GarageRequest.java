package com.gb.gaser.Garage.Dto;

import jakarta.validation.constraints.*;
import java.time.LocalTime;

public record GarageRequest(
        @NotBlank @Size(min = 2, max = 100) String garageName,
        @NotBlank @Pattern(regexp = "^[6-9][0-9]{9}$") String garagePhoneNumber,
        @NotBlank @Email @Size(max = 254) String garageMail,
        @NotNull LocalTime openingTime,
        @NotNull LocalTime closingTime
) {}

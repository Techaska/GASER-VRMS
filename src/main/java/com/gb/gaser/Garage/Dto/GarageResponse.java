package com.gb.gaser.Garage.Dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record GarageResponse(Long garageId, String garageName,
                             String garagePhoneNumber,
                             String garageMail, LocalDateTime createdAt,
                             LocalTime openingTime, LocalTime closingTime,
                             boolean approved) {}

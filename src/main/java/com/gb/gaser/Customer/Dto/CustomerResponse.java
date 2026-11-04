package com.gb.gaser.Customer.Dto;

public record CustomerResponse(
        Long id,
        String fullName,
        String email,
        String phoneNumber
) {}
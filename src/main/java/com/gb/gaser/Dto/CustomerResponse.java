package com.gb.gaser.Dto;

public record CustomerResponse(
        Long id,
        String fullName,
        String email,
        String phoneNumber
) {}
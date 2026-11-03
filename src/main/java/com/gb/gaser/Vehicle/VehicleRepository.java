package com.gb.gaser.Vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByCustomer_FullNameContainingIgnoreCase(String fullName);
    List<Vehicle> findByVehicleNumberContainingIgnoreCase(String vehicleNumber);
    List<Vehicle> findByBrandContainingIgnoreCase(String brand);
}
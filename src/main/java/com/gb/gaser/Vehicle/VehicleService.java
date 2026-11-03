
package com.gb.gaser.Vehicle;

import java.util.List;

public interface VehicleService {

    // Add Vehicle
    Vehicle addVehicle(Vehicle vehicle);

    // Get All Vehicles
    List<Vehicle> getAllVehicles();

    // Get Vehicle By Id
    Vehicle getVehicleById(Long id);

    // Update Vehicle
    Vehicle updateVehicle(Long id, Vehicle vehicle);

    // Delete Vehicle
    void deleteVehicle(Long id);

    // Search By Owner Name
    List<Vehicle> searchByOwner(String ownerName);

    // Search By Vehicle Number
    List<Vehicle> searchByVehicleNumber(String vehicleNumber);

    // Search By Brand
    List<Vehicle> searchByBrand(String brand);
}

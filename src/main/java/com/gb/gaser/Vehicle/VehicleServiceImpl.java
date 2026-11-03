
package com.gb.gaser.Vehicle;

import com.gb.gaser.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    // Add Vehicle
    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    // Get All Vehicles
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // Get Vehicle By Id
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vehicle not found with id: " + id
                        ));
    }

    // Update Vehicle
    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vehicle not found with id: " + id
                        ));

        vehicle.setVehicleNumber(updatedVehicle.getVehicleNumber());
        vehicle.setBrand(updatedVehicle.getBrand());
        vehicle.setModel(updatedVehicle.getModel());
        vehicle.setFuelType(updatedVehicle.getFuelType());
        vehicle.setPurchaseYear(updatedVehicle.getPurchaseYear());

        return vehicleRepository.save(vehicle);
    }

    // Delete Vehicle
    public void deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vehicle not found with id: " + id
                        ));

        vehicleRepository.delete(vehicle);
    }

    // Search By Owner Name
    public List<Vehicle> searchByOwner(String ownerName) {
        return vehicleRepository
                .findByCustomer_FullNameContainingIgnoreCase(ownerName);
    }

    // Search By Vehicle Number
    public List<Vehicle> searchByVehicleNumber(String vehicleNumber) {
        return vehicleRepository
                .findByVehicleNumberContainingIgnoreCase(vehicleNumber);
    }

    // Search By Brand
    public List<Vehicle> searchByBrand(String brand) {
        return vehicleRepository
                .findByBrandContainingIgnoreCase(brand);
    }
}

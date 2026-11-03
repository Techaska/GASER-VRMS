package com.gb.gaser.Vehicle;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gaser/vehicles")
@CrossOrigin(origins = "*")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@Valid @RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(vehicleService.addVehicle(vehicle));
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.getVehicleById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id,
                                                 @RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(
                vehicleService.updateVehicle(id, vehicle)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }

    @GetMapping("/search/owner")
    public ResponseEntity<List<Vehicle>> searchByOwner(@RequestParam String ownerName) {
        return ResponseEntity.ok(vehicleService.searchByOwner(ownerName));
    }

    @GetMapping("/search/vehicle-number")
    public ResponseEntity<List<Vehicle>> searchByVehicleNumber(@RequestParam String vehicleNumber) {
        return ResponseEntity.ok(vehicleService.searchByVehicleNumber(vehicleNumber));
    }

    @GetMapping("/search/brand")
    public ResponseEntity<List<Vehicle>> searchByBrand(@RequestParam String brand) {
        return ResponseEntity.ok(vehicleService.searchByBrand(brand));
    }
}
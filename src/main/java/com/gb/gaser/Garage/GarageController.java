package com.gb.gaser.Garage;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/gaser/garages")
public class GarageController {

    private final GarageService garageService;

    public GarageController(GarageService garageService) {
        this.garageService = garageService;
    }

    // Create a garage
    @PostMapping
    public Garage createGarage( @Valid @RequestBody Garage garage) {
        return garageService.createGarage(garage);
    }

    // Get all garages
    @GetMapping
    public List<Garage> getAllGarages() {
        return garageService.getAllGarages();
    }

    // Get garage by ID
    @GetMapping("/{id}")
    public Garage getGarageById(@PathVariable Long id) {
        return garageService.getGarageById(id);
    }

    // Update garage
    @PutMapping("/{id}")
    public Garage updateGarage(
            @Valid
            @PathVariable Long id,
            @RequestBody Garage garage) {
        return garageService.updateGarage(id, garage);
    }

    // Delete garage
    @DeleteMapping("/{id}")
    public String deleteGarage(@PathVariable Long id) {
        garageService.deleteGarage(id);
        return "Garage deleted successfully";
    }
}

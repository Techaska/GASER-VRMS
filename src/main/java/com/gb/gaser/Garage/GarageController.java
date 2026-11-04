package com.gb.gaser.Garage;

import com.gb.gaser.Garage.Dto.GarageRequest;
import com.gb.gaser.Garage.Dto.GarageResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/gaser/garages")
public class GarageController {

    private final GarageService garageService;

    public GarageController(GarageService garageService) {
        this.garageService = garageService;
    }

    // Create a garage
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Garage createGarage(@Valid @RequestBody GarageRequest garageRequest) {
        return garageService.createGarage(garageRequest);
    }

    // Get all garages
    @GetMapping
    public List<GarageResponse> getAllGarages() {
        return garageService.getAllGarages();
    }

    // Get garage by ID
    @GetMapping("/{id}")
    public GarageResponse getGarageById(@PathVariable Long id) {
        return garageService.getGarageById(id);
    }

    // Update garage
    @PutMapping("/{id}")
    public GarageResponse updateGarage(@Valid @PathVariable Long id,
                                      @RequestBody GarageRequest garageRequest) {
        return garageService.updateGarage(id, garageRequest);
    }

    // Delete garage
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteGarage(@PathVariable Long id) {
        garageService.deleteGarage(id);
        return "Garage deleted successfully";
    }
}

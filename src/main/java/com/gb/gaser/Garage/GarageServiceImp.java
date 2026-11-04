
package com.gb.gaser.Garage;

import com.gb.gaser.Exceptions.ResourceNotFoundException;
import com.gb.gaser.Garage.Dto.GarageRequest;
import com.gb.gaser.Garage.Dto.GarageResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GarageServiceImp implements GarageService {

    private final GarageRepository garageRepository;

    public GarageServiceImp(GarageRepository garageRepository) {
        this.garageRepository = garageRepository;
    }

    @Override
    @Transactional
    public Garage createGarage(GarageRequest garageRequest) {

        if (garageRepository.existsByGarageMail(garageRequest.garageMail())) {
            throw new IllegalArgumentException("Email already registered");
        }

        if (garageRepository.existsByGaragePhoneNumber(
                garageRequest.garagePhoneNumber())) {
            throw new IllegalArgumentException("Phone number already registered");
        }

        Garage garage = new Garage();
        garage.setGarageName(garageRequest.garageName());
        garage.setGarageMail(garageRequest.garageMail());
        garage.setGaragePhoneNumber(garageRequest.garagePhoneNumber());
        garage.setOpeningTime(garageRequest.openingTime());
        garage.setClosingTime(garageRequest.closingTime());

        return garageRepository.save(garage);
    }

    // Get all garages and return response DTOs.
    @Override
    public List<GarageResponse> getAllGarages() {
        return garageRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // Convert one Garage entity to its API response DTO.
    private GarageResponse toResponse(Garage garage) {
        return new GarageResponse(
                garage.getGarageId(),
                garage.getGarageName(),
                garage.getGaragePhoneNumber(),
                garage.getGarageMail(),
                garage.getCreatedAt(),
                garage.getOpeningTime(),
                garage.getClosingTime(),
                garage.isApproved()
        );
    }

    @Override
    public GarageResponse getGarageById(Long id) {
        return garageRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() ->
                        new RuntimeException("Garage not found with ID: " + id));
    }

    @Override
    @Transactional
    public GarageResponse updateGarage(Long id, GarageRequest request) {
        Garage garage = garageRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Garage not found with ID: " + id));

        if (!garage.getGarageMail().equals(request.garageMail())
                && garageRepository.existsByGarageMail(request.garageMail())) {
            throw new IllegalArgumentException("Email already registered");
        }

        if (!garage.getGaragePhoneNumber().equals(request.garagePhoneNumber())
                && garageRepository.existsByGaragePhoneNumber(request.garagePhoneNumber())) {
            throw new IllegalArgumentException("Phone number already registered");
        }

        garage.setGarageName(request.garageName());
        garage.setGarageMail(request.garageMail());
        garage.setGaragePhoneNumber(request.garagePhoneNumber());
        garage.setOpeningTime(request.openingTime());
        garage.setClosingTime(request.closingTime());

        return toResponse(garageRepository.save(garage));
    }

    @Override
    @Transactional
    public void deleteGarage(Long id) {
        Garage garage = garageRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Garage not found with ID: " + id));

        garageRepository.delete(garage);
    }

}

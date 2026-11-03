
package com.gb.gaser.Garage;

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
    public Garage createGarage(Garage garage) {

        if (garageRepository.existsByGarageMail(garage.getGarageMail())) {
            throw new IllegalArgumentException(
                    "Email already registered"
            );
        }

        if (garageRepository.existsByGaragePhoneNumber(
                garage.getGaragePhoneNumber())) {
            throw new IllegalArgumentException(
                    "Phone number already registered"
            );
        }

        // Save first to generate the database ID
        Garage savedGarage = garageRepository.save(garage);

        return garageRepository.save(savedGarage);
    }

    @Override
    public List<Garage> getAllGarages() {
        return garageRepository.findAll();
    }

    @Override
    public Garage getGarageById(Long id) {
        return garageRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Garage not found with ID: " + id));
    }

    @Override
    public Garage updateGarage(Long id, Garage garageDetails) {
        Garage garage = getGarageById(id);

        garage.setGarageName(garageDetails.getGarageName());
        garage.setGarageMail(garageDetails.getGarageMail());

        return garageRepository.save(garage);
    }

    @Override
    public void deleteGarage(Long id) {
        Garage garage = getGarageById(id);
        garageRepository.delete(garage);
    }
}

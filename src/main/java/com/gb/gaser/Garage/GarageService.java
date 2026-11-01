
package com.gb.gaser.Garage;

import java.util.List;

public interface GarageService {

    Garage createGarage(Garage garage);

    List<Garage> getAllGarages();

    Garage getGarageById(Long id);

    Garage updateGarage(Long id, Garage garageDetails);

    void deleteGarage(Long id);
}

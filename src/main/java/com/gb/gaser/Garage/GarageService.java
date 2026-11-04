
package com.gb.gaser.Garage;

import com.gb.gaser.Garage.Dto.GarageRequest;
import com.gb.gaser.Garage.Dto.GarageResponse;

import java.util.List;

public interface GarageService {

    Garage createGarage(GarageRequest garageRequest);

    List<GarageResponse> getAllGarages();

    GarageResponse getGarageById(Long id);

    GarageResponse updateGarage(Long id, GarageRequest garageRequest);

    void deleteGarage(Long id);
}

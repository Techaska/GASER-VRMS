package com.gb.gaser.Garage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarageRepository extends JpaRepository<Garage, Long> {

    boolean existsByGarageMail(String garageMail);

    boolean existsByGaragePhoneNumber(String garagePhoneNumber);

}

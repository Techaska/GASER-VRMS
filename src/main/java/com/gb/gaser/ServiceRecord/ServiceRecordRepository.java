package com.gb.gaser.ServiceRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, Long> {

    List<ServiceRecord> findByVehicleId(Long vehicleId);

    List<ServiceRecord> findByNextServiceDateBetween(LocalDate start, LocalDate end);

    @Query("SELECT SUM(s.cost) FROM ServiceRecord s")
    Double getTotalServiceCost();
}
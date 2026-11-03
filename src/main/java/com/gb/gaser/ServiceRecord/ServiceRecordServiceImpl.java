
package com.gb.gaser.ServiceRecord;

import com.gb.gaser.Exceptions.ResourceNotFoundException;
import com.gb.gaser.Vehicle.Vehicle;
import com.gb.gaser.Vehicle.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceRecordServiceImpl implements ServiceRecordService {

    private final ServiceRecordRepository serviceRecordRepository;

    private final VehicleRepository vehicleRepository;

    public ServiceRecordServiceImpl(ServiceRecordRepository serviceRecordRepository,
                                    VehicleRepository vehicleRepository) {
        this.serviceRecordRepository = serviceRecordRepository;
        this.vehicleRepository = vehicleRepository;
    }



    // Add Service Record
    public ServiceRecord addServiceRecord(ServiceRecord record) {
        Long vehicleId = record.getVehicle().getId();

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Vehicle not found with id: " + vehicleId
                        ));

        record.setVehicle(vehicle);
        return serviceRecordRepository.save(record);
    }

    // Get All Service Records
    public List<ServiceRecord> getAllServiceRecords() {
        return serviceRecordRepository.findAll();
    }

    // Get Service Records By Vehicle Id
    public List<ServiceRecord> getServiceRecordsByVehicleId(Long vehicleId) {
        return serviceRecordRepository.findByVehicleId(vehicleId);
    }

    // Get Service Record By Id
    public ServiceRecord getServiceRecordById(Long id) {
        return serviceRecordRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Service record not found with id: " + id
                        ));
    }

    // Update Service Record
    public ServiceRecord updateServiceRecord(
            Long id, ServiceRecord updatedRecord) {

        ServiceRecord record = serviceRecordRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Service record not found with id: " + id
                        ));

        record.setServiceDateTime(updatedRecord.getServiceDateTime());
        record.setServiceType(updatedRecord.getServiceType());
        record.setCost(updatedRecord.getCost());
        record.setOdometerReading(updatedRecord.getOdometerReading());
        record.setNextServiceDate(updatedRecord.getNextServiceDate());
        record.setRemarks(updatedRecord.getRemarks());

        return serviceRecordRepository.save(record);
    }

    // Delete Service Record
    public void deleteServiceRecord(Long id) {
        ServiceRecord record = serviceRecordRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Service record not found with id: " + id
                        ));

        serviceRecordRepository.delete(record);
    }
}

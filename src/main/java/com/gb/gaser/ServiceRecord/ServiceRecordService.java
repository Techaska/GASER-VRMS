
package com.gb.gaser.ServiceRecord;

import java.util.List;

public interface ServiceRecordService {

    // Add Service Record
    ServiceRecord addServiceRecord(ServiceRecord record);

    // Get All Service Records
    List<ServiceRecord> getAllServiceRecords();

    // Get Service Records By Vehicle Id
    List<ServiceRecord> getServiceRecordsByVehicleId(Long vehicleId);

    // Get Service Record By Id
    ServiceRecord getServiceRecordById(Long id);

    // Update Service Record
    ServiceRecord updateServiceRecord(
            Long id, ServiceRecord updatedRecord);

    // Delete Service Record
    void deleteServiceRecord(Long id);
}

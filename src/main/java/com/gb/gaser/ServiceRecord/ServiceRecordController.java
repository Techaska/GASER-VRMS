
package com.gb.gaser.ServiceRecord;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gaser/services")
@CrossOrigin(origins = "*")
public class ServiceRecordController {

    private final ServiceRecordService serviceRecordService;

    public ServiceRecordController(
            ServiceRecordService serviceRecordService) {
        this.serviceRecordService = serviceRecordService;
    }

    // Add Service Record
    @PostMapping
    public ResponseEntity<ServiceRecord> addServiceRecord(
            @Valid @RequestBody ServiceRecord serviceRecord) {
        return ResponseEntity.ok(
                serviceRecordService.addServiceRecord(serviceRecord)
        );
    }

    // Get All Service Records
    @GetMapping
    public ResponseEntity<List<ServiceRecord>> getAllServiceRecords() {
        return ResponseEntity.ok(
                serviceRecordService.getAllServiceRecords()
        );
    }

    // Get Service Record By Id
    @GetMapping("/{id}")
    public ResponseEntity<ServiceRecord> getServiceRecordById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                serviceRecordService.getServiceRecordById(id)
        );
    }

    // Get Service Records By Vehicle Id
    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<ServiceRecord>> getServiceRecordsByVehicleId(
            @PathVariable Long vehicleId) {
        return ResponseEntity.ok(
                serviceRecordService.getServiceRecordsByVehicleId(vehicleId)
        );
    }

    // Update Service Record
    @PutMapping("/{id}")
    public ResponseEntity<ServiceRecord> updateServiceRecord(
            @PathVariable Long id,
            @RequestBody ServiceRecord serviceRecord) {
        return ResponseEntity.ok(
                serviceRecordService.updateServiceRecord(id, serviceRecord)
        );
    }

    // Delete Service Record
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteServiceRecord(
            @PathVariable Long id) {
        serviceRecordService.deleteServiceRecord(id);
        return ResponseEntity.ok(
                "Service record deleted successfully"
        );
    }
}

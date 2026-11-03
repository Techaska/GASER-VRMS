package com.gb.gaser.Customer;

import com.gb.gaser.Dto.CustomerRequest;
import com.gb.gaser.Dto.CustomerResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gaser/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> addCustomer(
            @Valid @RequestBody CustomerRequest request) {

        CustomerResponse savedCustomer = customerService.addCustomer(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedCustomer);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(
            @PathVariable Long id) {

        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequest request) {

        return ResponseEntity.ok(
                customerService.updateCustomer(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerResponse> getCustomerByEmail(
            @PathVariable String email) {

        return ResponseEntity.ok(
                customerService.getCustomerByEmail(email)
        );
    }

    @GetMapping("/search")
    public ResponseEntity<List<CustomerResponse>> searchCustomersByName(
            @RequestParam String name) {

        return ResponseEntity.ok(
                customerService.searchCustomersByName(name)
        );
    }

    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<List<CustomerResponse>> getCustomersByPhoneNumber(
            @PathVariable String phoneNumber) {

        return ResponseEntity.ok(
                customerService.getCustomersByPhoneNumber(phoneNumber)
        );
    }
}
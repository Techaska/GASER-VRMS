package com.gb.gaser.Customer;

import com.gb.gaser.Customer.Dto.CustomerRequest;
import com.gb.gaser.Customer.Dto.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse addCustomer(CustomerRequest request);

    List<CustomerResponse> getAllCustomers();

    CustomerResponse getCustomerById(Long id);

    CustomerResponse updateCustomer(Long id, CustomerRequest request);

    void deleteCustomer(Long id);

    CustomerResponse getCustomerByEmail(String email);

    List<CustomerResponse> searchCustomersByName(String name);

    List<CustomerResponse> getCustomersByPhoneNumber(String phoneNumber);
}
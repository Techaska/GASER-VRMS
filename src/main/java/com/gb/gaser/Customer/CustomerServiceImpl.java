package com.gb.gaser.Customer;

import com.gb.gaser.Customer.Dto.CustomerRequest;
import com.gb.gaser.Customer.Dto.CustomerResponse;
import com.gb.gaser.Exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        if (customerRepository.existsByEmail(customerRequest.email())) {
            throw new IllegalArgumentException(
                    "A customer with this email already exists"
            );
        }

        Customer customer = new Customer();
        customer.setFullName(customerRequest.fullName());
        customer.setEmail(customerRequest.email());
        customer.setPhoneNumber(customerRequest.phoneNumber());

        Customer savedCustomer = customerRepository.save(customer);
        return toResponse(savedCustomer);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        return toResponse(findCustomerById(id));
    }

    @Override
    public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {
        Customer existingCustomer = findCustomerById(id);
        String newEmail = customerRequest.email();

        if (!newEmail.equals(existingCustomer.getEmail())
                && customerRepository.existsByEmail(newEmail)) {
            throw new IllegalArgumentException(
                    "A customer with this email already exists"
            );
        }

        existingCustomer.setFullName(customerRequest.fullName());
        existingCustomer.setEmail(newEmail);
        existingCustomer.setPhoneNumber(customerRequest.phoneNumber());

        Customer savedCustomer = customerRepository.save(existingCustomer);
        return toResponse(savedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = findCustomerById(id);
        customerRepository.delete(customer);
    }

    @Override
    public CustomerResponse getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer not found with email: " + email
                        )
                );

        return toResponse(customer);
    }

    @Override
    public List<CustomerResponse> searchCustomersByName(String name) {
        return customerRepository
                .findByFullNameContainingIgnoreCase(name)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public List<CustomerResponse> getCustomersByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private Customer findCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer not found with id: " + id
                        )
                );
    }

    private CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFullName(),
                customer.getEmail(),
                customer.getPhoneNumber()
        );
    }
}
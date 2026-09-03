package com.hotel.service;

import com.hotel.entity.Customer;
import com.hotel.repository.CustomerRepository;

import java.util.List;

public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Create Customer
    public void saveCustomer(Customer customer) {

        customerRepository.save(customer);
    }

    // Get Customer By ID
    public Customer getCustomerById(Long id) {

        return customerRepository.findById(id);
    }

    // Get All Customers
    public List<Customer> getAllCustomers() {

        return customerRepository.findAll();
    }
}
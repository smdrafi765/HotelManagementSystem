package com.hotel.controller;

import com.hotel.entity.Customer;
import com.hotel.service.CustomerService;

import java.util.List;

public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Create Customer
    public void createCustomer(Customer customer) {

        customerService.saveCustomer(customer);
    }

    // Get Customer By ID
    public Customer getCustomer(Long id) {

        return customerService.getCustomerById(id);
    }

    // Get All Customers
    public List<Customer> getAllCustomers() {

        return customerService.getAllCustomers();
    }
}
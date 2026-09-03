package com.hotel.repository;

import com.hotel.entity.Customer;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CustomerRepository {

    private EntityManager entityManager;

    public CustomerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Save Customer
    public void save(Customer customer) {

        entityManager.getTransaction().begin();

        entityManager.persist(customer);

        entityManager.getTransaction().commit();
    }

    // Find Customer By ID
    public Customer findById(Long id) {

        return entityManager.find(Customer.class, id);
    }

    // Update Customer
    public void update(Customer customer) {

        entityManager.getTransaction().begin();

        entityManager.merge(customer);

        entityManager.getTransaction().commit();
    }

    // Find All Customers
    public List<Customer> findAll() {

        return entityManager
                .createQuery(
                        "SELECT c FROM Customer c",
                        Customer.class
                )
                .getResultList();
    }
}
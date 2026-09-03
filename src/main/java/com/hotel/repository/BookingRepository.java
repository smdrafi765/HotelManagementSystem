package com.hotel.repository;

import com.hotel.entity.Booking;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class BookingRepository {

    private EntityManager entityManager;

    public BookingRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Save Booking
    public void save(Booking booking) {

        entityManager.getTransaction().begin();

        entityManager.persist(booking);

        entityManager.getTransaction().commit();
    }

    // Find Booking By ID
    public Booking findById(Long id) {

        return entityManager.find(Booking.class, id);
    }

    // Update Booking
    public void update(Booking booking) {

        entityManager.getTransaction().begin();

        entityManager.merge(booking);

        entityManager.getTransaction().commit();
    }

    // Check Room Availability
    public boolean isRoomAvailable(
            Long roomId,
            LocalDate checkInDate,
            LocalDate checkOutDate) {

        String jpql =
                "SELECT COUNT(b) FROM Booking b " +
                        "WHERE b.room.id = :roomId " +
                        "AND b.status = 'Booked' " +
                        "AND b.checkInDate < :checkOutDate " +
                        "AND b.checkOutDate > :checkInDate";

        Long count = entityManager
                .createQuery(jpql, Long.class)
                .setParameter("roomId", roomId)
                .setParameter("checkInDate", checkInDate)
                .setParameter("checkOutDate", checkOutDate)
                .getSingleResult();

        return count == 0;
    }

    // Find All Bookings
    public List<Booking> findAll() {

        return entityManager
                .createQuery(
                        "SELECT b FROM Booking b",
                        Booking.class
                )
                .getResultList();
    }
}
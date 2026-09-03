package com.hotel.repository;

import com.hotel.entity.Room;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class RoomRepository {

    private EntityManager entityManager;

    public RoomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Save Room
    public void save(Room room) {

        entityManager.getTransaction().begin();

        entityManager.persist(room);

        entityManager.getTransaction().commit();
    }

    // Find Room By ID
    public Room findById(Long id) {

        return entityManager.find(Room.class, id);
    }

    // Update Room
    public void update(Room room) {

        entityManager.getTransaction().begin();

        entityManager.merge(room);

        entityManager.getTransaction().commit();
    }

    // Find All Rooms
    public List<Room> findAll() {

        return entityManager
                .createQuery(
                        "SELECT r FROM Room r",
                        Room.class
                )
                .getResultList();
    }

    // Find Available Rooms
    public List<Room> findAvailableRooms(
            LocalDate checkInDate,
            LocalDate checkOutDate) {

        String jpql =
                "SELECT r FROM Room r " +
                        "WHERE r.id NOT IN (" +
                        "SELECT b.room.id FROM Booking b " +
                        "WHERE b.status = 'Booked' " +
                        "AND b.checkInDate < :checkOutDate " +
                        "AND b.checkOutDate > :checkInDate" +
                        ")";

        return entityManager
                .createQuery(jpql, Room.class)
                .setParameter("checkInDate", checkInDate)
                .setParameter("checkOutDate", checkOutDate)
                .getResultList();
    }
}
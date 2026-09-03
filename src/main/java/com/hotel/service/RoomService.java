package com.hotel.service;

import com.hotel.entity.Room;
import com.hotel.repository.RoomRepository;

import java.time.LocalDate;
import java.util.List;

public class RoomService {

    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    // Create Room
    public void saveRoom(Room room) {

        roomRepository.save(room);
    }

    // Get Room By ID
    public Room getRoomById(Long id) {

        return roomRepository.findById(id);
    }

    // Update Room Status
    public void updateRoomStatus(Long id, String status) {

        Room room =
                roomRepository.findById(id);

        if (room != null) {

            room.setStatus(status);

            roomRepository.update(room);

            System.out.println(
                    "Room status updated successfully!"
            );

        } else {

            System.out.println("Room not found!");
        }
    }

    // Get All Rooms
    public List<Room> getAllRooms() {

        return roomRepository.findAll();
    }

    // Find Available Rooms
    public List<Room> getAvailableRooms(
            LocalDate checkInDate,
            LocalDate checkOutDate) {

        return roomRepository.findAvailableRooms(
                checkInDate,
                checkOutDate
        );
    }
}
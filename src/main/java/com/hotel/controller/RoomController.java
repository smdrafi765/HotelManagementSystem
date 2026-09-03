package com.hotel.controller;

import com.hotel.entity.Room;
import com.hotel.service.RoomService;

import java.time.LocalDate;
import java.util.List;

public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // Create Room
    public void createRoom(Room room) {

        roomService.saveRoom(room);
    }

    // Get Room By ID
    public Room getRoom(Long id) {

        return roomService.getRoomById(id);
    }

    // Update Room Status
    public void updateRoomStatus(
            Long id,
            String status) {

        roomService.updateRoomStatus(id, status);
    }

    // Get All Rooms
    public List<Room> getAllRooms() {

        return roomService.getAllRooms();
    }

    // Find Available Rooms
    public List<Room> getAvailableRooms(
            LocalDate checkInDate,
            LocalDate checkOutDate) {

        return roomService.getAvailableRooms(
                checkInDate,
                checkOutDate
        );
    }
}
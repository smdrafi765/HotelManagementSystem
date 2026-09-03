package com.hotel.service;

import com.hotel.entity.Booking;
import com.hotel.entity.Room;
import com.hotel.repository.BookingRepository;
import com.hotel.repository.RoomRepository;

import java.util.List;

public class BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    public BookingService(
            BookingRepository bookingRepository,
            RoomRepository roomRepository) {

        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    // Create Booking
    public void saveBooking(Booking booking) {

        boolean available = bookingRepository.isRoomAvailable(
                booking.getRoom().getId(),
                booking.getCheckInDate(),
                booking.getCheckOutDate()
        );

        if (available) {

            bookingRepository.save(booking);

            Room room = roomRepository.findById(
                    booking.getRoom().getId()
            );

            if (room != null) {

                room.setStatus("Occupied");

                roomRepository.update(room);
            }

            System.out.println("Booking saved successfully!");

        } else {

            System.out.println(
                    "Room is not available for the selected dates!"
            );
        }
    }

    // Get Booking By ID
    public Booking getBookingById(Long id) {

        return bookingRepository.findById(id);
    }

    // Get All Bookings
    public List<Booking> getAllBookings() {

        return bookingRepository.findAll();
    }

    // Cancel Booking
    public void cancelBooking(Long id) {

        Booking booking =
                bookingRepository.findById(id);

        if (booking != null) {

            booking.setStatus("Cancelled");

            bookingRepository.update(booking);

            Room room = roomRepository.findById(
                    booking.getRoom().getId()
            );

            if (room != null) {

                room.setStatus("Available");

                roomRepository.update(room);
            }

            System.out.println(
                    "Booking cancelled successfully!"
            );

        } else {

            System.out.println("Booking not found!");
        }
    }
}
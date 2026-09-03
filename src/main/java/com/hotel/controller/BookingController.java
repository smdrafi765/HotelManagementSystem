package com.hotel.controller;

import com.hotel.entity.Booking;
import com.hotel.service.BookingService;

import java.util.List;

public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {

        this.bookingService = bookingService;
    }

    // Create Booking
    public void createBooking(Booking booking) {

        bookingService.saveBooking(booking);
    }

    // Get Booking By ID
    public Booking getBooking(Long id) {

        return bookingService.getBookingById(id);
    }

    // Get All Bookings
    public List<Booking> getAllBookings() {

        return bookingService.getAllBookings();
    }

    // Cancel Booking
    public void cancelBooking(Long id) {

        bookingService.cancelBooking(id);
    }
}
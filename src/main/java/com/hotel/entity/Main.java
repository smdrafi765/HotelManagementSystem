package com.hotel.entity;

import com.hotel.controller.BookingController;
import com.hotel.controller.CustomerController;
import com.hotel.controller.RoomController;
import com.hotel.repository.BookingRepository;
import com.hotel.repository.CustomerRepository;
import com.hotel.repository.RoomRepository;
import com.hotel.service.BookingService;
import com.hotel.service.CustomerService;
import com.hotel.service.RoomService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // =====================================
        // CREATE ENTITY MANAGER
        // =====================================

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("rafi");

        EntityManager manager =
                factory.createEntityManager();


        // =====================================
        // ROOM
        // =====================================

        RoomRepository roomRepository =
                new RoomRepository(manager);

        RoomService roomService =
                new RoomService(roomRepository);

        RoomController roomController =
                new RoomController(roomService);


        // Create Room
        Room room =
                new Room(
                        101,
                        "Deluxe",
                        2500,
                        "Available"
                );

        roomController.createRoom(room);

        System.out.println("Room saved successfully!");
        System.out.println("Room ID: " + room.getId());


        // Get Room By ID
        Room foundRoom =
                roomController.getRoom(room.getId());

        System.out.println();
        System.out.println("===== ROOM DETAILS =====");

        if (foundRoom != null) {

            System.out.println("ID          : "
                    + foundRoom.getId());

            System.out.println("Room Number : "
                    + foundRoom.getRoomNumber());

            System.out.println("Room Type   : "
                    + foundRoom.getRoomType());

            System.out.println("Price       : "
                    + foundRoom.getPrice());

            System.out.println("Status      : "
                    + foundRoom.getStatus());

        } else {

            System.out.println("Room not found!");
        }


        // =====================================
        // GET ALL ROOMS
        // =====================================

        List<Room> allRooms =
                roomController.getAllRooms();

        System.out.println();
        System.out.println("===== ALL ROOMS =====");

        for (Room r : allRooms) {

            System.out.println(
                    "ID: " + r.getId()
                            + " | Room Number: " + r.getRoomNumber()
                            + " | Type: " + r.getRoomType()
                            + " | Price: " + r.getPrice()
                            + " | Status: " + r.getStatus()
            );
        }
        // =====================================
// SEARCH AVAILABLE ROOMS
// =====================================

        LocalDate searchCheckIn =
                LocalDate.of(2026, 9, 15);

        LocalDate searchCheckOut =
                LocalDate.of(2026, 9, 17);

        List<Room> availableRooms =
                roomController.getAvailableRooms(
                        searchCheckIn,
                        searchCheckOut
                );

        System.out.println();
        System.out.println("===== AVAILABLE ROOMS =====");

        System.out.println(
                "Check-In  : " + searchCheckIn
        );

        System.out.println(
                "Check-Out : " + searchCheckOut
        );

        for (Room r : availableRooms) {

            System.out.println(
                    "ID: " + r.getId()
                            + " | Room Number: " + r.getRoomNumber()
                            + " | Type: " + r.getRoomType()
                            + " | Price: " + r.getPrice()
                            + " | Status: " + r.getStatus()
            );
        }


        // =====================================
        // CUSTOMER
        // =====================================

        CustomerRepository customerRepository =
                new CustomerRepository(manager);

        CustomerService customerService =
                new CustomerService(customerRepository);

        CustomerController customerController =
                new CustomerController(customerService);


        // Create Customer
        Customer customer =
                new Customer(
                        "Rafi",
                        "9876543210",
                        "rafi@gmail.com",
                        "Bengaluru"
                );

        customerController.createCustomer(customer);

        System.out.println();
        System.out.println("Customer saved successfully!");
        System.out.println("Customer ID: " + customer.getId());


        // Get Customer By ID
        Customer foundCustomer =
                customerController.getCustomer(customer.getId());

        System.out.println();
        System.out.println("===== CUSTOMER DETAILS =====");

        if (foundCustomer != null) {

            System.out.println("ID      : "
                    + foundCustomer.getId());

            System.out.println("Name    : "
                    + foundCustomer.getName());

            System.out.println("Phone   : "
                    + foundCustomer.getPhone());

            System.out.println("Email   : "
                    + foundCustomer.getEmail());

            System.out.println("Address : "
                    + foundCustomer.getAddress());

        } else {

            System.out.println("Customer not found!");
        }


        // =====================================
        // GET ALL CUSTOMERS
        // =====================================

        List<Customer> allCustomers =
                customerController.getAllCustomers();

        System.out.println();
        System.out.println("===== ALL CUSTOMERS =====");

        for (Customer c : allCustomers) {

            System.out.println(
                    "ID: " + c.getId()
                            + " | Name: " + c.getName()
                            + " | Phone: " + c.getPhone()
                            + " | Email: " + c.getEmail()
                            + " | Address: " + c.getAddress()
            );
        }


        // =====================================
        // BOOKING
        // =====================================

        BookingRepository bookingRepository =
                new BookingRepository(manager);

        BookingService bookingService =
                new BookingService(
                        bookingRepository,
                        roomRepository
                );

        BookingController bookingController =
                new BookingController(bookingService);


        // =====================================
        // FIRST BOOKING
        // =====================================

        Booking booking1 =
                new Booking(
                        LocalDate.of(2026, 9, 10),
                        LocalDate.of(2026, 9, 12),
                        "Booked",
                        customer,
                        room
                );

        bookingController.createBooking(booking1);

        System.out.println();
        System.out.println("===== FIRST BOOKING =====");

        System.out.println("Booking ID : "
                + booking1.getId());

        System.out.println("Check-In   : "
                + booking1.getCheckInDate());

        System.out.println("Check-Out  : "
                + booking1.getCheckOutDate());

        System.out.println("Status     : "
                + booking1.getStatus());


        // =====================================
        // SECOND BOOKING
        // OVERLAPPING DATES
        // =====================================

        Booking booking2 =
                new Booking(
                        LocalDate.of(2026, 9, 11),
                        LocalDate.of(2026, 9, 13),
                        "Booked",
                        customer,
                        room
                );

        bookingController.createBooking(booking2);

        System.out.println();
        System.out.println("===== SECOND BOOKING =====");

        System.out.println("Booking ID : "
                + booking2.getId());

        System.out.println("Check-In   : "
                + booking2.getCheckInDate());

        System.out.println("Check-Out  : "
                + booking2.getCheckOutDate());

        System.out.println("Status     : "
                + booking2.getStatus());


        // =====================================
        // GET FIRST BOOKING
        // =====================================

        Booking foundBooking =
                bookingController.getBooking(booking1.getId());

        System.out.println();
        System.out.println("===== BOOKING DETAILS =====");

        if (foundBooking != null) {

            System.out.println("Booking ID    : "
                    + foundBooking.getId());

            System.out.println("Check-In      : "
                    + foundBooking.getCheckInDate());

            System.out.println("Check-Out     : "
                    + foundBooking.getCheckOutDate());

            System.out.println("Status        : "
                    + foundBooking.getStatus());

            System.out.println("Customer ID   : "
                    + foundBooking.getCustomer().getId());

            System.out.println("Customer Name : "
                    + foundBooking.getCustomer().getName());

            System.out.println("Room ID       : "
                    + foundBooking.getRoom().getId());

            System.out.println("Room Number   : "
                    + foundBooking.getRoom().getRoomNumber());

        } else {

            System.out.println("Booking not found!");
        }


        // =====================================
        // GET ALL BOOKINGS
        // =====================================

        List<Booking> allBookings =
                bookingController.getAllBookings();

        System.out.println();
        System.out.println("===== ALL BOOKINGS =====");

        for (Booking b : allBookings) {

            System.out.println(
                    "Booking ID: " + b.getId()
                            + " | Check-In: " + b.getCheckInDate()
                            + " | Check-Out: " + b.getCheckOutDate()
                            + " | Status: " + b.getStatus()
                            + " | Customer ID: " + b.getCustomer().getId()
                            + " | Room ID: " + b.getRoom().getId()
            );
        }


        // =====================================
        // CANCEL FIRST BOOKING
        // =====================================

        bookingController.cancelBooking(booking1.getId());

        Booking cancelledBooking =
                bookingController.getBooking(booking1.getId());

        System.out.println();
        System.out.println("===== AFTER CANCELLING BOOKING =====");

        if (cancelledBooking != null) {

            System.out.println("Booking ID : "
                    + cancelledBooking.getId());

            System.out.println("Status     : "
                    + cancelledBooking.getStatus());

        } else {

            System.out.println("Booking not found!");
        }


        // =====================================
        // CLOSE
        // =====================================

        manager.close();
        factory.close();

        System.out.println();
        System.out.println("Program Executes Successfully");
    }
}
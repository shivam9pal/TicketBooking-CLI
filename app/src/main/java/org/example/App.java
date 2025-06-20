package org.example;

import org.example.entities.Train;
import org.example.entities.User;
import org.example.services.UserBookingService;
import org.example.util.UserServiceUtil;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class App {

    public static void main(String[] args) {
        System.out.println("Running Train Booking System");
        Scanner scanner = new Scanner(System.in);
        UserBookingService userBookingService;
        try {
            userBookingService = new UserBookingService();
        } catch (IOException ex) {
            System.out.println("Error initializing system: " + ex.getMessage());
            return;
        }
        Train trainSelectedForBooking = null; // Initialize to null
        while (true) {
            System.out.println("\nChoose option:");
            System.out.println("1. Sign up");
            System.out.println("2. Login");
            System.out.println("3. Fetch Bookings");
            System.out.println("4. Search Trains");
            System.out.println("5. Book a Seat");
            System.out.println("6. Cancel my Booking");
            System.out.println("7. Exit the App");
            int option = getValidIntInput(scanner, "Enter your choice (1-7)");
            switch (option) {
                case 1:
                    String nameToSignUp = getValidStringInput(scanner, "Enter the username to signup");
                    String passwordToSignUp = getValidStringInput(scanner, "Enter the password to signup");
                    User userToSignup = new User(nameToSignUp, passwordToSignUp, UserServiceUtil.hashPassword(passwordToSignUp), new ArrayList<>(), UUID.randomUUID().toString());
                    if (userBookingService.signUp(userToSignup)) {
                        System.out.println("Sign-up successful!");
                    } else {
                        System.out.println("Sign-up failed. Please try again.");
                    }
                    break;
                case 2:
                    String nameToLogin = getValidStringInput(scanner, "Enter the username to login");
                    String passwordToLogin = getValidStringInput(scanner, "Enter the password to login");
                    User userToLogin = new User(nameToLogin, passwordToLogin, UserServiceUtil.hashPassword(passwordToLogin), new ArrayList<>(), UUID.randomUUID().toString());
                    try {
                        userBookingService = new UserBookingService(userToLogin);
                        if (userBookingService.loginUser()) {
                            System.out.println("Login successful!");
                        } else {
                            System.out.println("Invalid username or password.");
                        }
                    } catch (IOException ex) {
                        System.out.println("Error during login: " + ex.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Fetching your bookings");
                    userBookingService.fetchBookings();
                    break;
                case 4:
                    String source = getValidStringInput(scanner, "Type your source station");
                    String dest = getValidStringInput(scanner, "Type your destination station");
                    List<Train> trains = userBookingService.getTrains(source, dest);
                    if (trains.isEmpty()) {
                        System.out.println("No trains found for the given route.");
                        break;
                    }
                    int index = 1;
                    for (Train t : trains) {
                        System.out.printf("%d. Train ID: %s, Train No: %s%n", index++, t.getTrainId(), t.getTrainNo());
                        for (Map.Entry<String, String> entry : t.getStationTimes().entrySet()) {
                            System.out.printf("  Station: %s, Time: %s%n", entry.getKey(), entry.getValue());
                        }
                    }
                    int trainIndex = getValidIntInput(scanner, "Select a train by typing 1,2,3...") - 1;
                    if (trainIndex >= 0 && trainIndex < trains.size()) {
                        trainSelectedForBooking = trains.get(trainIndex);
                    } else {
                        System.out.println("Invalid train selection.");
                    }
                    break;
                case 5:
                    if (trainSelectedForBooking == null || trainSelectedForBooking.getTrainId() == null) {
                        System.out.println("Please search and select a train first.");
                        break;
                    }
                    System.out.println("Seat Map ([ ] = Available, [X] = Booked):");
                    List<List<Integer>> seats = userBookingService.fetchSeats(trainSelectedForBooking);
                    for (int i = 0; i < seats.size(); i++) {
                        System.out.printf("Row %d: ", i);
                        for (Integer val : seats.get(i)) {
                            System.out.print(val == 0 ? "[ ]" : "[X]");
                        }
                        System.out.println();
                    }
                    int row = getValidIntInput(scanner, "Enter the row");
                    int col = getValidIntInput(scanner, "Enter the column");
                    String bookSource = getValidStringInput(scanner, "Enter source station");
                    String bookDest = getValidStringInput(scanner, "Enter destination station");
                    String dateInput = getValidStringInput(scanner, "Enter date of travel (YYYY-MM-DDThh:mm:ssZ)");
                    ZonedDateTime dateOfTravel;
                    try {
                        dateOfTravel = ZonedDateTime.parse(dateInput);
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format. Use YYYY-MM-DDThh:mm:ssZ");
                        break;
                    }
                    System.out.println("Booking your seat....");
                    Boolean booked = userBookingService.bookTrainSeat(trainSelectedForBooking, row, col, bookSource, bookDest, dateOfTravel);
                    if (booked) {
                        System.out.println("Booked! Enjoy your journey");
                    } else {
                        System.out.println("Can't book this seat");
                    }
                    break;
                case 6:
                    String ticketId = getValidStringInput(scanner, "Enter the ticket ID to cancel");
                    boolean cancelled = userBookingService.cancelBooking(ticketId);
                    if (cancelled) {
                        System.out.println("Booking cancelled successfully");
                    } else {
                        System.out.println("Failed to cancel booking");
                    }
                    break;
                case 7:
                    System.out.println("Exiting the App");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please choose 1-7.");
                    break;
            }
        }
    }

    private static int getValidIntInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                return scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    private static String getValidStringInput(Scanner scanner, String prompt) {
        System.out.println(prompt);
        scanner.nextLine(); // Clear buffer
        return scanner.nextLine();
    }
}
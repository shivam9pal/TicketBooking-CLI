package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Train;
import org.example.entities.User;
import org.example.entities.Ticket;
import org.example.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserBookingService {

    private static final Logger LOGGER = Logger.getLogger(UserBookingService.class.getName());
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<User> userList;
    private User user;
    private static final String USER_DB_PATH = "user.json";

    public UserBookingService(User user) throws IOException {
        this.user = user;
        loadUserListFromFile();
    }

    public UserBookingService() throws IOException {
        loadUserListFromFile();
    }

    private void loadUserListFromFile() throws IOException {
        File users = new File(getClass().getClassLoader().getResource(USER_DB_PATH).getFile());
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }

    public Boolean loginUser() {
        Optional<User> foundUser = userList.stream()
                .filter(user1 -> user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword()))
                .findFirst();
        if (foundUser.isPresent()) {
            this.user = foundUser.get();
            return true;
        }
        return false;
    }

    public Boolean signUp(User user1) {
        try {
            userList.add(user1);
            saveUserListToFile();
            return true;
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Failed to sign up user", ex);
            return false;
        }
    }

    private void saveUserListToFile() throws IOException {
        File usersFile = new File(getClass().getClassLoader().getResource(USER_DB_PATH).getFile());
        objectMapper.writeValue(usersFile, userList);
    }

    public void fetchBookings() {
        Optional<User> userFetched = userList.stream()
                .filter(user1 -> user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword()))
                .findFirst();
        if (userFetched.isPresent()) {
            userFetched.get().printTickets();
        } else {
            System.out.println("User not found or invalid credentials.");
        }
    }

    public Boolean cancelBooking(String ticketId) {
        if (ticketId == null || ticketId.isEmpty()) {
            System.out.println("Ticket ID cannot be null or empty.");
            return false;
        }
        Optional<Ticket> ticketToCancel = user.getTicketsBooked().stream()
                .filter(ticket -> ticketId.equals(ticket.getTicketId()))
                .findFirst();
        if (ticketToCancel.isPresent()) {
            Ticket ticket = ticketToCancel.get();
            user.getTicketsBooked().remove(ticket);
            try {
                // Free the seat in the train
                Train train = ticket.getTrain();
                List<List<Integer>> seats = train.getSeats();
                if (ticket.getRow() >= 0 && ticket.getSeat() >= 0) {
                    seats.get(ticket.getRow()).set(ticket.getSeat(), 0);
                    TrainService trainService = new TrainService();
                    trainService.updateTrain(train);
                }
                saveUserListToFile();
                System.out.println("Ticket with ID " + ticketId + " canceled successfully.");
                return true;
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Failed to cancel ticket", e);
                System.out.println("Error canceling ticket: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("No ticket found with ID " + ticketId);
            return false;
        }
    }

    public List<Train> getTrains(String source, String destination) {
        try {
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, destination);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Failed to fetch trains", ex);
            return new ArrayList<>();
        }
    }

    public List<List<Integer>> fetchSeats(Train train) {
        return train.getSeats();
    }

    public Boolean bookTrainSeat(Train train, int row, int seat, String source, String destination, ZonedDateTime dateOfTravel) {
        try {
            TrainService trainService = new TrainService();
            if (!trainService.validTrain(train, source, destination)) {
                System.out.println("Invalid source or destination for this train.");
                return false;
            }
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);
                    train.setSeats(seats);
                    trainService.updateTrain(train);
                    Ticket ticket = new Ticket(
                            java.util.UUID.randomUUID().toString(),
                            user.getUserId(),
                            source,
                            destination,
                            dateOfTravel,
                            train,
                            row,
                            seat
                    );
                    user.getTicketsBooked().add(ticket);
                    saveUserListToFile();
                    System.out.printf("Ticket booked: %s%n", ticket.getTicketInfo());
                    return true;
                } else {
                    System.out.println("Seat is already booked.");
                    return false;
                }
            } else {
                System.out.println("Invalid row or seat number.");
                return false;
            }
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Failed to book seat", ex);
            System.out.println("Error booking seat: " + ex.getMessage());
            return false;
        }
    }
}
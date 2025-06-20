package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.ZonedDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Ticket {

    private String ticketId;
    private String userId;
    private String source;
    private String destination;
    private ZonedDateTime dateOfTravel;
    private org.example.entities.Train train;
    private int row;
    private int seat;

    public Ticket() {}

    public Ticket(String ticketId, String userId, String source, String destination, ZonedDateTime dateOfTravel, org.example.entities.Train train, int row, int seat) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.source = source;
        this.destination = destination;
        this.dateOfTravel = dateOfTravel;
        this.train = train;
        this.row = row;
        this.seat = seat;
    }

    public String getTicketInfo() {
        return String.format("Ticket ID: %s belongs to User %s from %s to %s on %s, Seat: Row %d, Col %d",
                ticketId, userId, source, destination, dateOfTravel, row, seat);
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public ZonedDateTime getDateOfTravel() {
        return dateOfTravel;
    }

    public void setDateOfTravel(ZonedDateTime dateOfTravel) {
        this.dateOfTravel = dateOfTravel;
    }

    public org.example.entities.Train getTrain() {
        return train;
    }

    public void setTrain(org.example.entities.Train train) {
        this.train = train;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }
}
package org.example.entities;

import java.util.List;

public class User {
    private String name;

    private String password;

    private String hashedPassword;

    private List<Ticket> tickets;

    private String userId;

    public User(String name,String password, String hashedPassword ,List<Ticket> tickets ,String userId){
        this.name=name;
        this.password=password;
        this.hashedPassword=hashedPassword;
        this.tickets=tickets;
        this.userId=userId;
    }
    //default constructor for without value
    public User(){};

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }
    public String getHashedPassword(){
        return hashedPassword;
    }
    public List<Ticket> getTicketsBooked() {
        return getTicketsBooked();
    }
    public String getUserId(){
        return userId;
    }
}

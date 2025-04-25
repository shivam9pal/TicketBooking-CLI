package org.example.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.User;

import java.io.File;
import java.util.List;

public class UserBookingService {
    private User user;

    private List<User> userList;

    private ObjectMapper  objectMapper =new ObjectMapper();

    private static final String USER_PATH="../localDB/user.json";

    public UserBookingService(User user){
        this.user=user;
        File  users=new File(USER_PATH);

    }
}


package org.example;
import org.example.entities.User;
import org.example.services.UserBookingService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println("IRCTC Ticket Booking System");

        Scanner scanner =new Scanner(System.in);
        int option=0;
        try{
            UserBookingService userBookingService = new UserBookingService();
        }catch (IOException e) {
            System.out.println("there is something wrong");
            return;
        }

        while(option!=7){
            System.out.println("Choose Option");
            System.out.println("1 .For Sign Up");
            System.out.println("2 .For Login");
            System.out.println("3 .For Ftech Bookings");
            System.out.println("4 .For Search Trains");
            System.out.println("5 .For Seat Bookings");
            System.out.println("6 .For Cancel Bookings");
            System.out.println("7 . For Exit the App");
            option=scanner.nextInt();

            switch (option){
                case 1:
                    System.out.println("Enter the username to sign Up");
                    String nameToSignUp=scanner.next();
                    System.out.println("Enter the Password");
                    String passwrdToSignUp=scanner.next();
                    User userToSignUp=new User(nameToSignUp,passwrdToSignUp.UserServiceUtil.hashPassword(passwrdToSignUp),
                            new ArrayList<>(), UUID.randomUUID().toString());
                    UserBookingService.signUp(userToSignUp);
                    break;
            }
        }
    }
}

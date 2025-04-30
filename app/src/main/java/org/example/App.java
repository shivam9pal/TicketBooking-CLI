package org.example;
import org.example.entities.Train;
import org.example.entities.User;
import org.example.services.UserBookingService;
import org.example.util.UserServiceUtil;

import java.io.IOException;
import java.util.*;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println("IRCTC Ticket Booking System");

        Scanner scanner =new Scanner(System.in);
        int option=0;
        UserBookingService userBookingService;
        try{
             userBookingService = new UserBookingService();
        }catch (IOException e) {
            System.out.println("there is something wrong");
            return;
        }

        User loggedInUser=null;


        while(option!=7){
            System.out.println("Choose Option");
            System.out.println("1 .For Sign Up");
            System.out.println("2 .For Login");
            System.out.println("3 .For Fetch Bookings");
            System.out.println("4 .For Search Trains");
            System.out.println("5 .For Seat Bookings");
            System.out.println("6 .For Cancel Bookings");
            System.out.println("7 . For Exit the App");
            System.out.println("Enter the Option");
            option=scanner.nextInt();

            switch (option){
                case 1:
                    System.out.println("Enter the username to sign Up");
                    String nameToSignUp=scanner.next();
                    System.out.println("Enter the Password");
                    String passwrdToSignUp=scanner.next();

                    User userToSignUp=new User(nameToSignUp,passwrdToSignUp, UserServiceUtil.hashPassword(passwrdToSignUp),
                            new ArrayList<>(), UUID.randomUUID().toString());
                    userBookingService.signUp(userToSignUp);
                    break;

                case 2:
                    System.out.println("Enter the Username to login");
                    String nameToLogIn= scanner.next();
                    System.out.println("Enter the Password to login");
                    String passwordToLogIn=scanner.next();

                    User userToLogin=new User(nameToLogIn,passwordToLogIn,UserServiceUtil.hashPassword(passwordToLogIn),new ArrayList<>(),UUID.randomUUID().toString());
                    try{
                        userBookingService  =new UserBookingService(userToLogin);
                    } catch (IOException e) {
                        return;
                    }break;

                case 3:
                    System.out.println("Fetching your Bookings");
                    UserBookingService.fetchBooking();
                    break;
                case 4:
                    System.out.println("Type Your Source Station ");
                    String source=scanner.next();
                    System.out.println("Type Your Destination Station ");
                    String destination=scanner.next();
                    List<Train> trains=userBookingService.getTrains(source,destination);
                    int index =1;
                    for (Train t:trains){
                        System.out.println(index +"Train ID :"+t.getTrainId());
                        for(Map.Entry(String,String) entry: t.getStationTimes().entrySet()){
                            System.out.println("Station "+entry.getKey()+" time :"+ entry.getValue());

                        }
                    }

                    System.out.println("Select a tarin by typing 1,2,3,4");
                    trainSelectedForBooking = trains.get(scanner.nextInt());
                    break;

                case 5:
                    

            }
        }
    }
}
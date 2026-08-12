package ticket.booking;

import ticket.booking.entities.User;
import ticket.booking.services.UserBookingService;
import ticket.booking.util.UserServiceUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class App {

    public static void main(String[] args) {
        System.out.println("Running Train Booking System");
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        UserBookingService userBookingService;
        try {
            userBookingService = new UserBookingService();
        } catch (IOException ex) {
            System.out.println("Something went wrong");
            return;
        }

        while(option!=7) {
            System.out.println("Choose option");
            System.out.println("1. Sign up");
            System.out.println("2. Login");
            System.out.println("3. Fetch Booking");
            System.out.println("4. Search Trains");
            System.out.println("5. Book  seat");
            System.out.println("6. Cancel my Booking");
            System.out.println("7. Exit the App");
            option = scanner.nextInt();
            switch (option){
                case 1:
                    System.out.println("Enter the username to sign up");
                    String nameToSignUp = scanner.next();
                    System.out.println("Enter the password to sign up");
                    String passwordToSignUp = scanner.next();
                    User userToSignUp = new User(
                            nameToSignUp,
                            passwordToSignUp,
                            UserServiceUtil.hashPassword(passwordToSignUp),
                            new ArrayList<>(),
                            UUID.randomUUID().toString());
                    userBookingService.signUp(userToSignUp);
                    break;
                case 2:
                    System.out.println("Enter the username to Log in");
                    String nameToLogIn = scanner.next();
                    System.out.println("Enter the password to Log in");
                    String passwordToLogIn = scanner.next();
                    User userToLogIn = new User(
                            nameToLogIn,
                            passwordToLogIn,
                            UserServiceUtil.hashPassword(passwordToLogIn),
                            new ArrayList<>(),
                            UUID.randomUUID().toString());
                    try{
                        userBookingService = new UserBookingService(userToLogIn);
                    } catch(IOException ex) {
                        return;
                    }
                    break;
                case 3:
                    System.out.println("Fetching your bookings");
                    userBookingService.fetchBookings();
                case 4:
                case 5:
                case 6:
                    System.out.println("Provide the Ticket Id for the ticket you want to cancel");
                    String ticketId = scanner.next();
                    userBookingService.cancelBooking(ticketId);
                case 7:
            }
        }

    }
}

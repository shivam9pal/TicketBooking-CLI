# IRCTC Ticket Booking System (Java Console App)

## 📌 Overview
This is a **Java console-based** ticket booking system inspired by IRCTC.  
It allows users to:
- **Sign Up**
- **Login**
- **Search Trains**
- **Book Seats**
- **Fetch Bookings**
- **Cancel Bookings**

User data is stored in a **local JSON file** (`user.json`) for persistence.

---

## 🚀 Features
- **User Registration (Sign Up)**
- **Secure Password Handling** (hashed passwords using `UserServiceUtil`)
- **Login Verification**
- **View Bookings**
- **Book Tickets**
- **Cancel Bookings**
- **Persistent Storage** using JSON

---

## 📂 Project Structure
```
IRCTC-Booking/
│
├── src/main/java/org/example/
│   ├── App.java                # Main class
│   ├── entities/User.java      # User entity
│   ├── services/UserBookingService.java  # Booking and user operations
│   ├── util/UserServiceUtil.java # Utility for password hashing/checking
│
├── src/main/java/example/localDB/user.json  # Local database
│
└── README.md
```

---

## 💻 How It Works
1. **Sign Up**  
   User enters a username & password. Password is hashed before saving to `user.json`.

2. **Login**  
   Credentials are verified against stored hashed passwords.

3. **Fetch Bookings**  
   Displays all tickets booked by the logged-in user.

4. **Search Trains** *(Feature placeholder)*  
   Will allow searching trains from a mock database or API.

5. **Book Tickets** *(Feature placeholder)*  
   Adds a booking to the logged-in user.

6. **Cancel Bookings** *(Feature placeholder)*  
   Removes a booking from the user’s ticket list.

---

## 📖 Example Run
```
IRCTC Ticket Booking System
Choose Option
1 .For Sign Up
2 .For Login
3 .For Fetch Bookings
4 .For Search Trains
5 .For Seat Bookings
6 .For Cancel Bookings
7 .For Exit the App

Enter the Option: 1
Enter the username to sign Up: john
Enter the Password: 1234
Sign Up Successful!

Enter the Option: 2
Enter the Username to login: john
Enter the Password to login: 1234
Login Successful!
```

---

## ⚙️ How to Run
1. Clone the repository:
   ```sh
   git clone https://github.com/your-username/IRCTC-Booking.git
   ```
2. Open the project in your IDE (IntelliJ, Eclipse, etc.)
3. Make sure `user.json` exists at `src/main/java/example/localDB/user.json` with `[]` inside.
4. Run `App.java`.

---

## 📄 Learning Objectives
- Understanding **OOP in Java**
- **File handling** with JSON
- Using **Jackson ObjectMapper** for data storage
- Implementing **password hashing**
- Building a **menu-driven console application**

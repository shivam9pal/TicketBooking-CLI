# Train Ticket Booking CLI

A Java-based command-line application for booking train tickets, featuring user authentication, train search, seat booking, and ticket cancellation.

## Features
- User sign-up and login with BCrypt password hashing.
- Search trains by source and destination.
- Book and cancel seats with JSON-based persistence.
- Modular design with entity and service layers.
- JUnit tests for core functionalities.

## Setup
1. Clone the repository: `git clone github.com/yourusername/train-booking-cli`
2. Ensure Maven is installed.
3. Add dependencies via `pom.xml`.
4. Place `trains.json` and `user.json` in `src/main/resources`.
5. Run `mvn clean install` and execute `App.java`.

## Usage
Run the application and choose options (1-7) to interact with the system. Example:
- Sign up or login to access features.
- Search trains by entering source and destination stations.
- Book a seat by selecting a train and entering row/column.

## Future Improvements
- Add SQLite for scalable persistence.
- Implement REST API for web-based access.
- Enhance CLI with JLine for better interactivity.
package com.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Random;

public class Database {

    private static final String URL = "jdbc:sqlite:database.db";
    private static Connection conn = null;

    /**
     * Open a database connection.
     */
    public static void openConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(URL);
        }
    }

    /**
     * Close the database connection.
     */
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Failed to close the database connection: " + e.getMessage());
            }
        }
    }

    // Modify your existing methods to use the static `conn` field.
    public static void executeUpdate(String sql, Object... params) throws SQLException {
        openConnection();  // Ensure connection is open
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            pstmt.executeUpdate();
        } finally {
            closeConnection();  // Close connection after the operation
        }
    }

    public static ResultSet query(String sql, Object... params) throws SQLException {
        openConnection();  // Ensure connection is open
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            return pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            if (pstmt != null) pstmt.close();
            closeConnection();  // Ensure connection is closed on error
            throw e;
        }
        // Connection should not be closed here if ResultSet is still open and being used outside this method
    }

    public static void initialize() throws SQLException {
        
        openConnection();
        
        String dsql = "DROP TABLE Flights";
        try {
            executeUpdate(dsql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String dsql2 = "DROP TABLE Bookings";
        try {
            executeUpdate(dsql2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String dsql3 = "DROP TABLE Users";
        try {
            executeUpdate(dsql3);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String sql = """
                   CREATE TABLE IF NOT EXISTS Flights (
                       flightID INTEGER PRIMARY KEY AUTOINCREMENT,
                       dep VARCHAR(255),
                       arr VARCHAR(255),
                       depT STRING,
                       arrT STRING,
                       seats INT,
                       seatsAvailable INT,
                       price DOUBLE PRECISION
                   );
                """;

        try {
            executeUpdate(sql);
            System.out.println("Flight Table created or already exists.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String sql2 = "INSERT INTO Flights(dep, arr, depT, arrT, seats, seatsAvailable, price) VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Array of possible destinations
        String[] destinations = { "Reykjavik", "London", "Akureyri", "New York" };
        LocalDateTime currentTime = LocalDateTime.now();
        Integer seats = 200;
        Integer seatsAvailable = 200;
        Double price = 15000.00;
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            // Randomly select departure and arrival destinations ensuring they are not the
            // same
            int depIndex = random.nextInt(destinations.length);
            int arrIndex;
            do {
                arrIndex = random.nextInt(destinations.length);
            } while (arrIndex == depIndex); // Ensure departure and arrival locations are different

            String dep = destinations[depIndex];
            String arr = destinations[arrIndex];

            // For demonstration, adding one hour to each subsequent flight's departure and
            // arrival times
            LocalDateTime depTime = currentTime.plusHours(i);
            LocalDateTime arrTime = depTime.plusHours(7); // Assuming a 7-hour flight duration for simplicity

            String depT = depTime.toString();
            String arrT = arrTime.toString();

            try {
                executeUpdate(sql2, dep, arr, depT, arrT, seats, seatsAvailable, price);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        String sql3 = """
                   CREATE TABLE IF NOT EXISTS Bookings (
                       bookingID INTEGER PRIMARY KEY AUTOINCREMENT,
                       serviceName TEXT NOT NULL,
                       servicePrice REAL NOT NULL,
                       serviceDescription TEXT,
                       flightID INTEGER,
                       userID INTEGER,
                       seats INTEGER NOT NULL,
                       FOREIGN KEY (flightID) REFERENCES Flights(flightID),
                       FOREIGN KEY (userID) REFERENCES Users(userID)
                   );
                """;

        try {
            executeUpdate(sql3);
            System.out.println("BookingTable created or already exists.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String sql4 = """
                   CREATE TABLE IF NOT EXISTS Users (
                       userID INTEGER PRIMARY KEY AUTOINCREMENT,
                       name TEXT
                   );
                """;

        try {
            executeUpdate(sql4);
            System.out.println("UserTable created or already exists.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        closeConnection();
    }

    public static void main(String[] args) {
        try {
            initialize();   
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

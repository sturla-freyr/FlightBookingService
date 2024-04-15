package com.example.database;

import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.Random;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

    private static final String URL = "jdbc:sqlite:database.db";

    // Get a database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    /**
     * Execute a SQL query with parameters.
     *
     * @param sql    SQL query with placeholders.
     * @param params Parameters to replace placeholders in the SQL query.
     * @return ResultSet containing the query results.
     * @throws SQLException If an error occurs during query execution.
     */
    public static ResultSet query(String sql, Object... params) throws SQLException {
        Connection conn = getConnection(); // Get connection
        PreparedStatement pstmt = conn.prepareStatement(sql); // Prepare statement

        // Set parameters
        for (int i = 0; i < params.length; i++) {
            pstmt.setObject(i + 1, params[i]);
        }

        return pstmt.executeQuery(); // Execute query
    }

    /**
     * Execute an SQL DDL or DML command that does not return a ResultSet.
     *
     * @param sql    SQL command with placeholders.
     * @param params Parameters to replace placeholders in the SQL command.
     * @throws SQLException If an error occurs during command execution.
     */
    public static void executeUpdate(String sql, Object... params) throws SQLException {
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set parameters
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }

            pstmt.executeUpdate();
        }
    }

    public static void initialize() {
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
        Integer seatsAvailable = 150;
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
    }

    public static void main(String[] args) {
        initialize();
    }
}

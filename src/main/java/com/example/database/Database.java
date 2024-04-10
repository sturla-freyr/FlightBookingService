package com.example.database;

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
     * @param sql SQL query with placeholders.
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
     * @param sql SQL command with placeholders.
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


    public static void main(String[] args) {
        // here for testing during development will likely be removed

        String sql = """
            CREATE TABLE IF NOT EXISTS Flights (
                id integer PRIMARY KEY,
                name text NOT NULL
            );
                 """;

    try {
        executeUpdate(sql);
        System.out.println("Table created or already exists.");
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }

    try {
        // Query the sqlite_master table to retrieve all table names
        ResultSet rs = query("SELECT name FROM sqlite_master WHERE type ='table' AND name NOT LIKE 'sqlite_%';");
    
        // Iterate over the ResultSet directly without calling rs.beforeFirst();
        while (rs.next()) {
            System.out.println("Found table: " + rs.getString("name"));
        }
    } catch (SQLException e) {
        System.out.println("Database error: " + e.getMessage());
        e.printStackTrace();
    }

    }
}

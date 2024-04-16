package com.example.repository;

import com.example.database.Database;

import javafx.scene.chart.PieChart.Data;

import com.example.User;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UserRepo {
    User[] users;

    public UserRepo(){
        users = null;
    }

    public User findUserById(int id) throws SQLException{
        User user = null;
        String sql = "SELECT * FROM Users WHERE userID = ?";
        Database.openConnection();
        ResultSet rs = null;
        try {
            rs = Database.query(sql, id);
            if (rs.next()) {
                String name = rs.getString("name");
                user = new User(rs.getInt("userID"), name);
            } else {
                System.out.println("No Users found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } finally {
            if (rs != null) rs.close(); // Ensure ResultSet is closed properly
        }
        Database.closeConnection();
        return user;
    }

    public int addUser(String name) {
        String sql = "INSERT INTO Users(name) VALUES(?) RETURNING userID";
        
        try { 
            ResultSet rs = Database.query(sql, name); // Execute the update
            if (rs.next()) {  // Move cursor to the first record
                int id = rs.getInt("userID");  // Retrieve the userID
                return id;  // Return the fetched id
            } else {
                return -1; // No rows returned, handle according to your logic
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } 
        return -1; // Indicate failure if the try block didn't return true
    }

}

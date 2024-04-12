package com.example.repository;

import com.example.database.Database;
import com.example.User;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UserRepo {
    User[] users;

    public UserRepo(){
        users = null;
    }

    public User findUserById(int id) {
        User user = null;
        String sql = "SELECT * FROM Users WHERE userID = ?";

        try {
            ResultSet rs = Database.query(sql, id);
            if (rs.next()) {
                user = new User(rs.getInt("userID"), rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        
        return user;
    }

    public boolean addUser(String name) {
        String sql = "INSERT INTO Users(name) VALUES(?)";
        
        try { 
            Database.executeUpdate(sql, name); // Execute the update
            return true; // Success
            
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return false; // Indicate failure if the try block didn't return true
    }
    
}

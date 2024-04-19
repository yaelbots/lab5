package edu.utsa.cs3443.ibs074_lab5.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private String realName;
    private List<Role> roles;

    // Constructor
    public User(String username, String password, String realName) {
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.roles = new ArrayList<>();
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRealName() {
        return realName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    // Method to validate user credentials
    public static boolean validate(String username, String password) {
        // Implement logic to validate user from users.csv file
        // Return true if user exists and password matches, false otherwise
        return false; // Placeholder
    }
}

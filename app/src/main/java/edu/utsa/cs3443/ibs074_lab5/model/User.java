package edu.utsa.cs3443.ibs074_lab5.model;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class User {
    private String username;
    private String password;
    private String realName;
    private List<String> roles;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRealName() {
        return realName;
    }

    public List<String> getRoles() {
        return roles;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    // Constructor, getters, and setters for username, password, realName, and roles

    // Method to validate user credentials and fetch roles
    public static User validateAndGetUser(Context context, String username, String password) {
        // Your validation logic here, return null if credentials are invalid

        // If credentials are valid, fetch user details including roles from database or file
        User user = fetchUserFromDatabaseOrFile(context, username);

        return user;
    }


    private static User fetchUserFromDatabaseOrFile(Context context, String username) {
        // Fetch user details including roles from database or file
        // Implement your logic here to fetch roles associated with the given username

        // For demonstration, let's assume roles are stored in a CSV file named "users.csv"
        try {
            InputStream inputStream = context.getAssets().open("users.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4 && parts[0].equals(username)) {
                    User user = new User();
                    user.setUsername(parts[0]);
                    user.setPassword(parts[1]);
                    user.setRealName(parts[2]);
                    user.setRoles(Arrays.asList(parts[3].split("\\s*,\\s*"))); // Split roles by comma
                    return user;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}



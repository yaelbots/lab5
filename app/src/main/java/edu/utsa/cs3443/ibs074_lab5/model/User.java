package edu.utsa.cs3443.ibs074_lab5.model;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {
    private String username;
    private String password;
    private String realName;
    private List<String> roles;

    // Constructor, getters, and setters for username, password, realName, and roles

    // Method to validate user credentials and fetch roles
    public static User validateAndGetUser(Context context, String username, String password) {
        User user = fetchUserFromDatabaseOrFile(context, username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public static User fetchUserFromDatabaseOrFile(Context context, String username) {
        try {
            InputStream inputStream = context.getAssets().open("users.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 4); // Split into 4 parts: username, password, real name, roles
                if (parts.length == 4 && parts[0].equals(username)) {
                    String[] roles = parts[3].split(","); // Split the roles by comma
                    List<String> userRoles = new ArrayList<>();
                    for (String role : roles) {
                        userRoles.add(role.trim());
                    }
                    User user = new User();
                    user.setUsername(parts[0]);
                    user.setPassword(parts[1]);
                    user.setRealName(parts[2]);
                    user.setRoles(userRoles);
                    return user;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

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
}

package edu.utsa.cs3443.ibs074_lab5.model;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user with associated credentials and roles.
 * @author Yael Reyes ibs074
 */
public class User {
    private String username;
    private String password;
    private String realName;
    private List<String> roles;

    /**
     * Validates the user credentials and retrieves user roles from a data source.
     *
     * @param context  The application context.
     * @param username The username to validate.
     * @param password The password associated with the username.
     * @return The user object if validation successful, null otherwise.
     */
    public static User validateAndGetUser(Context context, String username, String password) {
        User user = fetchUserFromDatabaseOrFile(context, username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    /**
     * Fetches a user from a database or file based on the username.
     *
     * @param context  The application context.
     * @param username The username to fetch.
     * @return The user object if found, null otherwise.
     */
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

    /**
     * Gets the username of the user.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the real name of the user.
     *
     * @return The real name.
     */
    public String getRealName() {
        return realName;
    }

    /**
     * Gets the roles associated with the user.
     *
     * @return The roles.
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * Sets the username of the user.
     *
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the real name of the user.
     *
     * @param realName The real name to set.
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * Sets the roles associated with the user.
     *
     * @param roles The roles to set.
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

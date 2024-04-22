package edu.utsa.cs3443.ibs074_lab5.model;

import java.util.List;

public class Scene {
    private String title;
    private List<Role> roles;

    // Constructor
    public Scene(String title, List<Role> roles) {
        this.title = title;
        this.roles = roles;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public List<Role> getRoles() {
        return roles;
    }
}

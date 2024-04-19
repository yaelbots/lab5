package edu.utsa.cs3443.ibs074_lab5.model;

import java.util.List;

public class Scene {
    private int sceneId;
    private String title;
    private List<Role> roles;

    // Constructor
    public Scene(int sceneId, String title, List<Role> roles) {
        this.sceneId = sceneId;
        this.title = title;
        this.roles = roles;
    }

    // Getters
    public int getSceneId() {
        return sceneId;
    }

    public String getTitle() {
        return title;
    }

    public List<Role> getRoles() {
        return roles;
    }
}

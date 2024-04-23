package edu.utsa.cs3443.ibs074_lab5.model;

import java.util.List;

/**
 * The Scene class represents a scene in an act.
 * It contains a title and a list of roles that are in this scene.
 * @author Yael Reyes ibs074
 */
public class Scene {
    // The title of the scene
    private String title;
    // The list of roles in the scene
    private List<Role> roles;

    /**
     * Constructor for creating a Scene instance with a specified title and list of roles.
     *
     * @param title The title of the scene.
     * @param roles The list of roles in the scene.
     */
    public Scene(String title, List<Role> roles) {
        this.title = title;
        this.roles = roles;
    }

    /**
     * Gets the title of the scene.
     *
     * @return The title of the scene.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the list of roles in the scene.
     *
     * @return The list of roles in the scene.
     */
    public List<Role> getRoles() {
        return roles;
    }
}

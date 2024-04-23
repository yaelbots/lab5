package edu.utsa.cs3443.ibs074_lab5.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Act class represents an act in a play or performance.
 * It contains a list of scenes that belong to this act.
 * @author Yael Reyes ibs074
 */
public class Act {
    // The number of the act
    private int actNumber;
    // The list of scenes in the act
    private List<Scene> scenes;

    /**
     * Constructor for creating an Act instance with a specified act number.
     *
     * @param actNumber The number of the act.
     */
    public Act(int actNumber) {
        this.actNumber = actNumber;
        this.scenes = new ArrayList<>();
    }

    /**
     * Adds a scene to the act.
     *
     * @param scene The scene to add.
     */
    public void addScene(Scene scene) {
        this.scenes.add(scene);
    }

    /**
     * Gets the number of the act.
     *
     * @return The act number.
     */
    public int getActNumber() {
        return actNumber;
    }

    /**
     * Gets the list of scenes in the act.
     *
     * @return The list of scenes.
     */
    public List<Scene> getScenes() {
        return scenes;
    }
}

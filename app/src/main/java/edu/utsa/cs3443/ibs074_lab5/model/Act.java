package edu.utsa.cs3443.ibs074_lab5.model;

import java.util.ArrayList;
import java.util.List;

public class Act {
    private int actNumber;
    private List<Scene> scenes;

    // Constructor
    public Act(int actNumber) {
        this.actNumber = actNumber;
        this.scenes = new ArrayList<>();
    }
    public void addScene(Scene scene) {
        this.scenes.add(scene);
    }
    // Getter
    public int getActNumber() {
        return actNumber;
    }

    public List<Scene> getScenes() {
        return scenes;
    }
}

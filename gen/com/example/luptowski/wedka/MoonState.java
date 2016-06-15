package com.example.luptowski.wedka;

import java.io.Serializable;

/**
 * Created by lukas on 11.05.2016.
 */
public class MoonState implements Serializable {

    private int id;
    private String description;
    private String imageURL;

    public MoonState(){};

    public MoonState(String description, String imageURL){
        this.description = description;
        this.imageURL = imageURL;
    };


    @Override
    public String toString() {
        return "Dzień: [id=" + id + ", description"+description+", imageURL"+imageURL+"]";
    }

    public String getImageURL() { return imageURL; }
    public String getDescription() { return description; }
    public void setId(int id) { this.id = id; }
    public int getId() { return id; }
}


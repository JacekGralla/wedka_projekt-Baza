package com.example.luptowski.wedka;

import java.io.Serializable;

/**
 * Created by lukas on 11.05.2016.
 */
public class Condition implements Serializable {

    private int id;
    private String description;
    private String imageURL;

    public Condition(){};

    public Condition(String description, String imageURL){

        this.description = description;
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Pogoda: [id=" + id + ", description=" + description + ", imageURL=" + imageURL + "]";

    }

    public String getDescription() { return description; }
    public String getImageURL() { return imageURL; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
}

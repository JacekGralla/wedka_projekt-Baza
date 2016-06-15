package com.example.luptowski.wedka;

import java.io.Serializable;

/**
 * Created by lukas on 11.05.2016.
 */
public class Ranking implements Serializable {
    private int id;
    private int place;
    private int userID;
    private int fishID;

    Ranking(){}

    Ranking(int place, int userID, int fishID){
        this.place = place;
        this.userID = userID;
        this.fishID = fishID;
    }

    @Override
    public String toString() {
        return "Miejsce: [id="+id+", place="+place+", userID="+userID+", fishID="+fishID+"]";
    }

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }
    public int getFishID() { return fishID; }
    public int getPlace() { return place; }
    public int getUserID() { return userID; }
}

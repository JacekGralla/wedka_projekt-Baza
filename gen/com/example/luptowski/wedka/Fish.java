package com.example.luptowski.wedka;

import java.io.Serializable;

/**
 * Created by lukas on 11.05.2016.
 */
public class Fish implements Serializable {
    private int id;
    private String name;
    private float length;
    private float weight;

    Fish(){}

    Fish(String name, float length, float weight){
        this.name = name;
        this.length = length;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Ryba: [id="+id+", name="+name+", length="+length+", weight="+weight+"]";
    }

    public String getName() { return name; }
    public float getLength() { return length; }
    public float getWeight() { return weight; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
}

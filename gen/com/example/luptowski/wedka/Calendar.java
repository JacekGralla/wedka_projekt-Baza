package com.example.luptowski.wedka;

import java.io.Serializable;

/**
 * Created by lukas on 11.05.2016.
 */
public class Calendar implements Serializable {
    private int id;
    private String date;
    private String dayName;
    private String sunrise;
    private String sunset;
    private int moonStateId;
    private int conditionStateId;


    public Calendar() {}

    public Calendar(String date, String dayName, String sunrise, String sunset, int moonStateId, int conditionStateId) {
        super();
        this.date = date;
        this.dayName = dayName;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.moonStateId = moonStateId;
        this.conditionStateId = conditionStateId;
    }

    @Override
    public String toString() {
        return "Dzień: [id=" + id + ", date=" + date + ", dayName=" + dayName +", sunrise"+sunrise + ", sunset" + sunset + ", moonStateId" +moonStateId+", conditionStateId"+conditionStateId + "]";
    }

    public int getConditionStateId() { return conditionStateId; }
    public int getMoonStateId() { return moonStateId; }
    public String getDate() { return date; }
    public String getDayName() { return dayName; }
    public String getSunrise() { return sunrise; }
    public String getSunset() { return sunset; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
}
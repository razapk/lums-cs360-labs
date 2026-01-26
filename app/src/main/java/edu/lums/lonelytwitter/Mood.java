package edu.lums.lonelytwitter;

import java.util.Date;

public abstract class Mood {
    private Date date;

    // Constructors
    public Mood() {
        this.date = new Date();
    }

    public Mood(Date date) {
        this.date = date;
    }

    // Getter
    public Date getDate() {
        return date;
    }

    // Setter
    public void setDate(Date date) {
        this.date = date;
    }

    // Abstract method
    public abstract String getMoodType();
}

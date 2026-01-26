package edu.lums.lonelytwitter;

import java.util.Date;

public class HappyMood extends Mood {
    // Constructors
    public HappyMood() {
        super();
    }

    public HappyMood(Date date) {
        super(date);
    }

    // Implementation of the abstract method from Mood class
    @Override
    public String getMoodType() {
        return "Happy :)";
    }
}

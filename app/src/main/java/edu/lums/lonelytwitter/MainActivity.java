package edu.lums.lonelytwitter;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        playStuff();
    }

    protected void playStuff() {
        // Lab exercise
        List<Mood> moods = new ArrayList<>();
        moods.add(new HappyMood());
        moods.add(new SadMood());

        // Let's modify a mood to hundred years after epoch
        moods.get(0).setDate(new Date(100L * 365L * 24L * 60L * 60L * 1000L));

        // Let's print all of them
        for (Mood mood : moods) {
            Log.e("MOOD", mood.getMoodType() + " at " + mood.getDate().toString());
        }

        // Lab activity
        List<Tweet> tweets = new ArrayList<>();
        tweets.add(new ImportantTweet("This is an important tweet!"));
        tweets.add(new NormalTweet("This is a normal tweet."));

        for (Tweet tweet : tweets) {
            Log.e("TWEET", tweet.getMessage() + " | Important: " + tweet.isImportant());
        }
    }
}
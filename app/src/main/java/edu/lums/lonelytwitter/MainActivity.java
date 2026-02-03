package edu.lums.lonelytwitter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import edu.lums.lonelytwitter.R;

public class MainActivity extends AppCompatActivity {
    protected Button addButton;
    protected Button removeButton;
    protected Button confirmButton;
    protected EditText editText;
    protected ListView listView;
    protected View addView;

    protected List<String> data;

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

        // Initialize UI components
        addButton = findViewById(R.id.add_city_button);
        removeButton = findViewById(R.id.remove_city_button);
        confirmButton = findViewById(R.id.confirm_button);
        editText = findViewById(R.id.edit_text);
        listView = findViewById(R.id.list);
        addView = findViewById(R.id.add_layout);
        data = new ArrayList<>();

        // Set click listeners
        addButton.setOnClickListener(v -> addButtonClicked());
        removeButton.setOnClickListener(v -> removeButtonClicked());
        confirmButton.setOnClickListener(v -> confirmButtonClicked());
    }

    protected void addButtonClicked() {
        addView.setVisibility(View.VISIBLE);
    }

    protected void removeButtonClicked() {
        // Implement removal logic here
        Log.i("MainActivity", "Remove button clicked");
    }

    protected void confirmButtonClicked() {
        String cityName = editText.getText().toString();
        if (!cityName.isEmpty()) {
            data.add(cityName);
            // Update ListView adapter here
            Log.i("MainActivity", "Added city: " + cityName);
        }
        addView.setVisibility(View.GONE);
        editText.setText("");
    }
}
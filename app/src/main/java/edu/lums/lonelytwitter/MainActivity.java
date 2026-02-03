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
import java.util.List;

public class MainActivity extends AppCompatActivity {
    protected Button addButton;
    protected Button removeButton;
    protected Button confirmButton;
    protected EditText editText;
    protected ListView listView;
    protected View addView;

    protected List<String> data;
    protected CityListAdapter cityListAdapter;
    protected int selectedPosition = -1;

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

        // Initialize adapter and set it to ListView
        cityListAdapter = new CityListAdapter(this, data);
        listView.setAdapter(cityListAdapter);

        // Set click listeners
        addButton.setOnClickListener(v -> addButtonClicked());
        removeButton.setOnClickListener(v -> removeButtonClicked());
        confirmButton.setOnClickListener(v -> confirmButtonClicked());

        // Set item click listener for selection
        listView.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            removeButton.setEnabled(true);
            Log.i("MainActivity", "Selected city at position: " + position);
        });
    }

    protected void addButtonClicked() {
        addView.setVisibility(View.VISIBLE);
    }

    protected void removeButtonClicked() {
        if (selectedPosition >= 0 && selectedPosition < data.size()) {
            String removedCity = data.get(selectedPosition);
            cityListAdapter.removeCity(selectedPosition);
            Log.i("MainActivity", "Removed city: " + removedCity);
            selectedPosition = -1;
            removeButton.setEnabled(false);
        } else {
            Log.i("MainActivity", "No city selected to remove");
        }
    }

    protected void confirmButtonClicked() {
        String cityName = editText.getText().toString().trim();
        if (!cityName.isEmpty()) {
            cityListAdapter.addCity(cityName);
            Log.i("MainActivity", "Added city: " + cityName);
        }
        addView.setVisibility(View.GONE);
        editText.setText("");
    }
}
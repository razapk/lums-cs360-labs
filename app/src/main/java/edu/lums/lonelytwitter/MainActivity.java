package edu.lums.lonelytwitter;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityDialogListener {
    protected FloatingActionButton addButton;
    protected ListView listView;
    protected ArrayList<City> dataList;

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
        addButton = findViewById(R.id.add_button);
        listView = findViewById(R.id.list);

        // Initialize adapter and set it to ListView
        dataList = new ArrayList<>();
        String[] cities = {"Edmonton", "Vancouver", "Toronto"};
        String[] provinces = {"AB", "BC", "ON"};
        for (int i = 0; i < cities.length; i++) {
            dataList.add(new City(cities[i], provinces[i]));
        }
        cityListAdapter = new CityListAdapter(this, dataList);
        listView.setAdapter(cityListAdapter);

        // Set click listeners
        addButton.setOnClickListener(v -> addButtonClicked());

        // Set item click listener for selection
        listView.setOnItemClickListener((parent, view, position, id) -> {
            editClicked(position);
        });
    }

    protected void addButtonClicked() {
        new AddCityFragment().show(getSupportFragmentManager(), "AddCityFragment");
        selectedPosition = -1;
    }

    protected void editClicked(int position) {
        selectedPosition = position;
        City city = cityListAdapter.getItem(position);
        if (city != null) {
            Bundle arguments = new Bundle();
            arguments.putSerializable("city", city);
            AddCityFragment fragment = new AddCityFragment();
            fragment.setArguments(arguments);
            fragment.show(getSupportFragmentManager(), "EditCityFragment");
        }
    }

    @Override
    public void addCity(City city) {
        if (selectedPosition >= 0) {
            cityListAdapter.updateCity(selectedPosition, city);
        } else {
            cityListAdapter.addCity(city);
        }
    }
}
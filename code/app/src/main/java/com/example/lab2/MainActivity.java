package com.example.lab2;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
public class MainActivity extends AppCompatActivity {

    ListView cityList;
    EditText inputCity;
    Button addCityButton, deleteCityButton;

    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    String selectedCity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        inputCity = findViewById(R.id.input_city);
        addCityButton = findViewById(R.id.add_city_button);
        deleteCityButton = findViewById(R.id.delete_city_button);

        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedCity = dataList.get(position);
            Toast.makeText(this, "Selected: " + selectedCity, Toast.LENGTH_SHORT).show();
            deleteCityButton.setVisibility(View.VISIBLE);
        });

        addCityButton.setOnClickListener(v -> {
            String cityName = inputCity.getText().toString().trim();
            if (!cityName.isEmpty() && !dataList.contains(cityName)) {
                dataList.add(cityName);
                cityAdapter.notifyDataSetChanged();
                inputCity.setText("");
                Toast.makeText(this, cityName + "added.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "City is Invalid.", Toast.LENGTH_SHORT).show();
            }
        });

        deleteCityButton.setOnClickListener(v -> {
            if (selectedCity != null) {
                dataList.remove(selectedCity);
                cityAdapter.notifyDataSetChanged();
                deleteCityButton.setVisibility(View.GONE);
                selectedCity = null;
                Toast.makeText(this, "City deleted.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No city selected.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CountryDetails extends AppCompatActivity {
    private int positionCountry;
    private Button tips;
    private TextView tvCountryName,tvCases,tvActive,tvDeaths,tvRecovered,tvTodayCases,tvTodayDeaths,tvFatal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);


        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position",0);

        tips = findViewById(R.id.tips_2);
        tvActive = findViewById(R.id.tVActiveCases);
        tvCountryName = findViewById(R.id.tVCountryName);
        tvCases = findViewById(R.id.tVCases);
        tvDeaths = findViewById(R.id.tVDeaths);
        tvFatal = findViewById(R.id.tVFatal);
        tvRecovered = findViewById(R.id.tVRecovered);
        tvTodayCases = findViewById(R.id.tVTodayCases);
        tvTodayDeaths = findViewById(R.id.tVTodayDeaths);

        tvCountryName.setText(Details.countriesList.get(positionCountry).getCountry());
        tvActive.setText(Details.countriesList.get(positionCountry).getActive());
        tvCases.setText(Details.countriesList.get(positionCountry).getCases());
        tvDeaths.setText(Details.countriesList.get(positionCountry).getDeaths());
        tvRecovered.setText(Details.countriesList.get(positionCountry).getRecovered());
        tvFatal.setText(Details.countriesList.get(positionCountry).getCritical());
        tvTodayCases.setText(Details.countriesList.get(positionCountry).getTodayCases());
        tvTodayDeaths.setText(Details.countriesList.get(positionCountry).getTodayDeaths());

        tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Tips_1.class));
            }
        });


    }
}
package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.google.firebase.auth.FirebaseAuth;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class Tracker extends AppCompatActivity {

    TextView tvCases , tvActive , tvDeaths , tvTodayCases , tvRecovered , tvTodayDeaths ,tvCountries ;
    SimpleArcLoader simpleArcLoader;
    PieChart pieChart;
 //   ScrollView scrollView;
    SwipeButton swipeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);
        tvCases = findViewById(R.id.tVCases);
        tvActive = findViewById(R.id.tVActiveCases);
        tvTodayCases = findViewById(R.id.tVTodayCases);
        tvTodayDeaths = findViewById(R.id.tVTodayDeaths);
        tvDeaths =findViewById(R.id.tVDeaths);
        tvRecovered = findViewById(R.id.tVRecovered);
        tvCountries = findViewById(R.id.tVCountries);
        pieChart = findViewById(R.id.pieChart);
        simpleArcLoader = findViewById(R.id.loader);
       // scrollView = findViewById(R.id.sV);
        swipeButton = findViewById(R.id.swipe);


        fetchData();

    }

    private void fetchData() {

        String url = "https://disease.sh/v2/all ";

        simpleArcLoader.start();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject (response.toString());

                            tvCases.setText(jsonObject.getString("cases"));
                            tvActive.setText(jsonObject.getString("active"));
                            tvDeaths.setText(jsonObject.getString("deaths"));
                            tvRecovered.setText(jsonObject.getString("recovered"));
                            tvTodayCases.setText(jsonObject.getString("todayCases"));
                            tvTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                            tvCountries.setText(jsonObject.getString("affectedCountries"));

                            pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tvActive.getText().toString()), Color.parseColor("#00b6ff")));
                            pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(tvDeaths.getText().toString()), Color.parseColor("#ff0c00")));
                            pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(tvRecovered.getText().toString()), Color.parseColor("#3aff00")));
                            pieChart.startAnimation();

                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                            //scrollView.setVisibility(View.VISIBLE);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                            //scrollView.setVisibility(View.VISIBLE);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
              //  scrollView.setVisibility(View.VISIBLE);
                Toast.makeText(Tracker.this,"ERROR",Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

        swipeButton.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                startActivity(new Intent(getApplicationContext(),Details.class));
            }
        });

    }
    }
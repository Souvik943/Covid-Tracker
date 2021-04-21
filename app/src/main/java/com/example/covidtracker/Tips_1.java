package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Tips_1 extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout Dots_Layout;
    private Button button;
    private ImageView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_1);

        viewPager = findViewById(R.id.pager);

        IntroAdapter adapter = new IntroAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        button = findViewById(R.id.tracker);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Tracker.class));
            }
        });
        Dots_Layout = (LinearLayout)findViewById(R.id.dots_layout);
        createDots(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                createDots(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void createDots(int crr_pos) {

        if(Dots_Layout !=null)
            Dots_Layout.removeAllViews();

        dots = new ImageView[5];

        for(int i =0 ; i<5 ; i++){
            dots[i] = new ImageView(this);
            if(i == crr_pos) {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.act_dots));
            }
            else {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.inact_dots));
            }

         LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(4,0,4,0);

                Dots_Layout.addView(dots[i],params);
        }

    }
}
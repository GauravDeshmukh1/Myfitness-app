package com.example.fitness_app;


import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class AboutUs extends AppCompatActivity {
    ConstraintLayout expandableview_step;
    Button button_arrow_step;
    CardView cardView_step;
    ConstraintLayout expandableview_benefit;
    Button button_arrow_benefit;
    CardView cardView_benefit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("About Us");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        expandableview_step = findViewById(R.id.expandableview_step);
        button_arrow_step = findViewById(R.id.button_arrow_step);
        cardView_step = findViewById(R.id.cardview);

        button_arrow_step.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (expandableview_step.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardView_step,new AutoTransition());
                    expandableview_step.setVisibility(View.VISIBLE);
                    button_arrow_step.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                    expandableview_benefit.setVisibility(View.GONE);
                    button_arrow_benefit.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }else{
                    TransitionManager.beginDelayedTransition(cardView_step,new AutoTransition());
                    expandableview_step.setVisibility(View.GONE);
                    button_arrow_step.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });


        expandableview_benefit = findViewById(R.id.expandableview_benefit);
        button_arrow_benefit = findViewById(R.id.button_arrow_benefit);
        cardView_benefit = findViewById(R.id.cardview);

        button_arrow_benefit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if(expandableview_benefit.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardView_benefit,new AutoTransition());
                    expandableview_benefit.setVisibility(View.VISIBLE);
                    button_arrow_benefit.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                    expandableview_step.setVisibility(View.GONE);
                    button_arrow_step.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }else{
                    TransitionManager.beginDelayedTransition(cardView_benefit,new AutoTransition());
                    expandableview_benefit.setVisibility(View.GONE);
                    button_arrow_benefit.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

    }
}
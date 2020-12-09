package com.example.fitness_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.BuildConfig;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import Adapters.main_course_adapter;
import Classes.RecyclerItemClickListener;
import Models.main_course_model;


//NavigationView.OnNavigationItemSelectedListener,
public class main_courses extends AppCompatActivity implements  DatePickerDialog.OnDateSetListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    RecyclerView recyclerView;
    public int sub_button;

    @SuppressLint({"ResourceType", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_courses);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.Workout);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //menu
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.menu_view);
        //navigationView.setNavigationItemSelectedListener(this);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                switch (id)
                {
                    case R.id.menu_logout:
                        item.setChecked(true);
                        startActivity(new Intent(getApplicationContext(), startPage.class));
                        finish();
                        break;
                    case  R.id.menu_calendar:
                        DialogFragment datePicker = new Classes.DatePickerFragmnet();
                        datePicker.show(getSupportFragmentManager(), "date picker");
                        break;
                    case  R.id.menu_profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        break;
                    case R.id.menu_workout:
                        startActivity(new Intent(getApplicationContext(), main_courses.class));
                        break;
                    case R.id.menu_about:
                        startActivity(new Intent(getApplicationContext(), AboutUs.class));
                        break;
                    case R.id.menu_timer:
                        startActivity(new Intent(getApplicationContext(), timer.class));
                        break;
                    case R.id.menu_music:
                        startActivity(new Intent(getApplicationContext(), Music.class));
                        break;
                    case R.id.menu_invite:
                        try {
                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Perfect Health");
                            String sharemsg = "Perfect Health\nhttps://play.google.com/store/apps/details?=en" + BuildConfig.APPLICATION_ID + "\n\n";
                            intent.putExtra(Intent.EXTRA_TEXT, sharemsg);
                            startActivity(Intent.createChooser(intent, "Share by"));
                        } catch (Exception e) {

                        }
                        break;
                    default:
                        startActivity(new Intent(getApplicationContext(), Music.class));
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        recyclerView = findViewById(R.id.recyclerView);

        ArrayList<main_course_model> list = new ArrayList<>();

        list.add(new main_course_model(R.drawable.weight_loss_));
        list.add(new main_course_model(R.drawable.full_bdy));
        list.add(new main_course_model(R.drawable.arm_bignner));
        list.add(new main_course_model(R.drawable.arm_intermediate_bdy));
        list.add(new main_course_model(R.drawable.arm_advance_bdy));
        list.add(new main_course_model(R.drawable.shoulder_back_bignner));
        list.add(new main_course_model(R.drawable.shoulder_back_intermediate_bdy));
        list.add(new main_course_model(R.drawable.shoulder_back_advance_bdy));
        list.add(new main_course_model(R.drawable.chest_bignner_bdy));
        list.add(new main_course_model(R.drawable.chest_intermediate_bdy));
        list.add(new main_course_model(R.drawable.chest_advance_bdy));
        list.add(new main_course_model(R.drawable.abs_bignner));
        list.add(new main_course_model(R.drawable.abs_intermediate_bdy));
        list.add(new main_course_model(R.drawable.abs_advance_bdy));
        list.add(new main_course_model(R.drawable.lower_bdy));
        list.add(new main_course_model(R.drawable.leg_bigger_bdy));
        list.add(new main_course_model(R.drawable.leg_intermediate_bdy));
        list.add(new main_course_model(R.drawable.leg_advance_bdy));

        main_course_adapter adapter = new main_course_adapter(list, this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                sub_button = position;
                Intent intent = new Intent(main_courses.this, sub_courses.class);
                intent.putExtra("sub_button", sub_button);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

    }

    //menu
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        drawerLayout.closeDrawer(GravityCompat.START);
//        int id = item.getItemId();
//
//        switch (id)
//        {
//            case R.id.menu_logout:
//                item.setChecked(true);
//                startActivity(new Intent(getApplicationContext(), startPage.class));
//                finish();
//            case  R.id.menu_calendar:
//                DialogFragment datePicker = new Classes.DatePickerFragmnet();
//                datePicker.show(getSupportFragmentManager(), "date picker");
//            case  R.id.menu_profile:
//                startActivity(new Intent(getApplicationContext(), Profile.class));
//            case R.id.menu_workout:
//                startActivity(new Intent(getApplicationContext(), main_courses.class));
//            case R.id.menu_about:
//                startActivity(new Intent(getApplicationContext(), AboutUs.class));
//            case R.id.menu_timer:
//                startActivity(new Intent(getApplicationContext(), timer.class));
//            case R.id.menu_music:
//                startActivity(new Intent(getApplicationContext(), Music.class));
//            case R.id.menu_invite:
//                try {
//                    Intent intent = new Intent(Intent.ACTION_SEND);
//                    intent.setType("text/plain");
//                    intent.putExtra(Intent.EXTRA_SUBJECT, "Perfect Health");
//                    String sharemsg = "Perfect Health\nhttps://play.google.com/store/apps/details?=en" + BuildConfig.APPLICATION_ID + "\n\n";
//                    intent.putExtra(Intent.EXTRA_TEXT, sharemsg);
//                    startActivity(Intent.createChooser(intent, "Share by"));
//                } catch (Exception e) {
//                    Toast.makeText(this, "Error Occured", Toast.LENGTH_SHORT).show();
//                }
//        }
//        return true;
//    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

    }

}
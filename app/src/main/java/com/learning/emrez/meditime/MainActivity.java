package com.learning.emrez.meditime;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.IPieInfo;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends AppCompatActivity implements IPieInfo {
    BottomNavigationView bottomNavigationView;
    private LinearLayout stats;
    private LinearLayout dashboard;
    private LinearLayout about;
    private RecyclerView recyclerView;
    private MedicineAdapter adapter;
    private List<Medicine> medicineList;
    private FancyButton addMed;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_nav);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        stats = (LinearLayout) findViewById(R.id.Statistics);
        dashboard = (LinearLayout) findViewById(R.id.Dashboard);
        about = (LinearLayout) findViewById(R.id.About);
        addMed = (FancyButton) findViewById(R.id.add_medicine);
        context = getApplicationContext();


        //addMed onClick
        addMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),ScreenSlidePagerActivity.class);
                startActivity(intent);
            }
        });

        //initial screen setup
        stats.setVisibility(View.GONE);
        dashboard.setVisibility(View.VISIBLE);
        about.setVisibility(View.GONE);

        //recyclerview-works
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        medicineList = new ArrayList<>();
        adapter = new MedicineAdapter(this, medicineList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareMedicines();

        //Create pie chart
        AnimatedPieView mAnimatedPieView = findViewById(R.id.animatedPieView);
        AnimatedPieViewConfig config = new AnimatedPieViewConfig();
        config.startAngle(-90)// Starting angle offset
                .addData(new SimplePieInfo(30, Color.RED, "Used"))//Data (bean that implements the IPieInfo interface)
                .addData(new SimplePieInfo(70, Color.GREEN, "Not used"))
                .duration(2000)// draw pie animation duration
                .drawText(true)
                .textSize(20)// Text description size
                .textMargin(8)// Margin between text and guide line
                .canTouch(true);
        // The following two sentences can be replace directly 'mAnimatedPieView.start (config); '
        mAnimatedPieView.applyConfig(config);
        mAnimatedPieView.start();

        //Create bottom navigation view
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.ic_statistics:
                                stats.setVisibility(View.VISIBLE);
                                dashboard.setVisibility(View.GONE);
                                about.setVisibility(View.GONE);
                                break;
                            case R.id.ic_dashboard:
                                stats.setVisibility(View.GONE);
                                dashboard.setVisibility(View.VISIBLE);
                                about.setVisibility(View.GONE);
                                break;
                            case R.id.ic_about:
                                stats.setVisibility(View.GONE);
                                dashboard.setVisibility(View.GONE);
                                about.setVisibility(View.VISIBLE);
                                break;
                        }
                        return true;
                    }
                });

        //dialog for adding medicine

    }

    //PieChart Library Methods (3)
    @Override
    public float getValue() {
        // This return value will determine the percentage
        return 0.5f;
    }

    @Override
    public int getColor() {
        // This return value will determine the cur-pie color，please return @colorInt，but not @colorRes
        return Color.GREEN;
    }

    @Override
    public String getDesc() {
        // description text, may not return
        return "Description Text";
    }



    private void prepareMedicines() {


        Medicine a = new Medicine("Aspirin");
        medicineList.add(a);

        a = new Medicine("B");
        medicineList.add(a);

        a = new Medicine("C");
        medicineList.add(a);

        a = new Medicine("D");
        medicineList.add(a);

        a = new Medicine("E");
        medicineList.add(a);


        adapter.notifyDataSetChanged();
    }



}

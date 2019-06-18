package com.example.veggiebowl;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

import me.itangqi.waveloadingview.WaveLoadingView;

public class activity_progress extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    LineChart tchart,rchart;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    ProgressBar bar;

    WaveLoadingView loading;

    int progress = activity_second.dayCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        navigationView = (NavigationView) findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        rchart = findViewById(R.id.rchart);
        //ListView lv=findViewById(R.id.listview1);
        tchart=findViewById(R.id.tchart);

//        rchart.setOnChartGestureListener(activity_progress.this);
//        rchart.setOnChartValueSelectedListener(activity_progress.this);

//        rchart.setDragEnabled(true);
//        rchart.setScaleEnabled(false);

        bar = findViewById(R.id.pbar);
        loading = findViewById(R.id.loading);

        loading.setProgressValue(0);

        bar.setMax(100);
        bar.setProgress(progress);
        loading.setProgressValue(progress);

        if (progress < 50) {
            loading.setTopTitle("");
            loading.setCenterTitle(String.valueOf(progress + "%"));
            //loading.setBottomTitle(String.valueOf(progress + "%"));
            loading.setWaveColor(Color.YELLOW);
            if (progress < 20) {
                loading.setWaveColor(Color.RED);

            }
        }

        else if (progress <= 80) {
            loading.setBottomTitle("");
            loading.setCenterTitle(String.valueOf(progress + "%"));
            loading.setTopTitle("");
            loading.setWaveColor(Color.GREEN);
            if(progress <70){

                loading.setWaveColor(Color.YELLOW);


            }




        }
        else {
            //loading.setTopTitle(String.valueOf(progress + "%"));
            loading.setCenterTitle(String.valueOf(progress + "%"));
            loading.setBottomTitle("");
            loading.setWaveColor(Color.GREEN);
        }



        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        ArrayList<Entry> yValues = new ArrayList<>();
//
//        yValues.add(new Entry(0,60f));
//        yValues.add(new Entry(1,50f));
//        yValues.add(new Entry(2,45f));
//        yValues.add(new Entry(3,98f));
//        yValues.add(new Entry(4,20f));
//        yValues.add(new Entry(5,55f));
//        yValues.add(new Entry(6,-64f));


        ArrayList<Entry> yValues = new ArrayList<>();

        yValues.add(new Entry(10,10f));
        yValues.add(new Entry(20,20f));
        yValues.add(new Entry(30,30f));
        yValues.add(new Entry(40,40f));
        yValues.add(new Entry(50,10f));
        yValues.add(new Entry(60,60f));
        yValues.add(new Entry(70,20f));

//        yValues.add(new Entry(80,activity_second.dayCount));
//        yValues.add(new Entry(90,activity_second.dayCount));

//
//        ArrayList<Entry> values = new ArrayList<>();
//
//        yValues.add(new Entry(0,10f));
//        yValues.add(new Entry(15,20f));
//        yValues.add(new Entry(30,30f));
//        yValues.add(new Entry(45,40f));
//        //yValues.add(new Entry(50,50f));
//        yValues.add(new Entry(60,60f));
//        yValues.add(new Entry(75,70f));
//        //yValues.add(new Entry(80,80f));
//        yValues.add(new Entry(90,90f));
//        if(activity_second.dayCount>=0 && activity_second.dayCount<15) {
//            values.add(new Entry(10, activity_second.dayCount));
//
//        }
//        if(activity_second.dayCount>=15 && activity_second.dayCount<30) {
//            values.add(new Entry(20, activity_second.dayCount));
//        }
//        if(activity_second.dayCount>30 && activity_second.dayCount<=45) {
//            values.add(new Entry(45, activity_second.dayCount));
//        }
//        if(activity_second.dayCount>45 && activity_second.dayCount<=60) {
//            values.add(new Entry(60, activity_second.dayCount));
//        }
//        if(activity_second.dayCount>60 && activity_second.dayCount<=75) {
//            values.add(new Entry(75, activity_second.dayCount));
//        }
//        if(activity_second.dayCount>75 && activity_second.dayCount<=90) {
//            values.add(new Entry(90, activity_second.dayCount));
//        }


        //setData(activity_second.dayCount);



        ////////////////////////////////
        // Y axis values (value in linechart)
        ////////////////////////////////
        ArrayList<Entry> values = new ArrayList<>();
        values.add(new Entry(activity_second.dayCount,activity_second.dayCount));

        int count=90;
        for (int i = 0; i < count; i++) {

            if (i == activity_second.dayCount) {
                values.add(new Entry(i, activity_second.dayCount));
            }
            else {
                values.add(new Entry(i,i));
            }
            i+=15;
        }


        LimitLine upper= new LimitLine(80f,"Harvest");
        upper.setLineWidth(4f);
        upper.enableDashedLine(10f,10f,0);
        upper.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upper.setTextSize(10f);

        LimitLine upper1= new LimitLine(10f,"Germination");
        upper1.setLineWidth(4f);
        upper1.enableDashedLine(10f,10f,0);
        upper1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        upper1.setTextSize(10f);

        LimitLine upper2= new LimitLine(50f,"Vegitative Growth");
        upper2.setLineWidth(4f);
        upper2.enableDashedLine(10f,10f,0);
        upper2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        upper2.setTextSize(10f);




        YAxis left=rchart.getAxisLeft();
        left.removeAllLimitLines();
        left.addLimitLine(upper);
        left.addLimitLine(upper1);
        left.addLimitLine(upper2);
        //LineData data = new LineData(xVals, set1);
        LineDataSet set1 = new LineDataSet(yValues,"Water Level");
        LineDataSet set2 = new LineDataSet(values,"Plant growth");
        set1.setFillAlpha(100);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        ArrayList<ILineDataSet> dataSets1 = new ArrayList<>();
        dataSets1.add(set2);

        LineData data = new LineData(dataSets);

        LineData data1 = new LineData(dataSets1);
        set1.setLineWidth(3f);
        set1.setHighLightColor(Color.BLUE);
        set1.setColor(Color.GREEN);
        set1.setDrawCircles(false);
        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set1.setCubicIntensity(0.1f);
        set2.setColor(Color.RED);
        set2.setDrawCircles(false);
        set2.setHighLightColor(Color.GREEN);
        set2.setLineWidth(3f);
        set2.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set2.setCubicIntensity(0.1f);
        tchart.setData(data);
        tchart.getXAxis().setDrawGridLines(false);
        tchart.getAxisLeft().setDrawGridLines(false);
        rchart.setData(data1);
        rchart.getXAxis().setDrawGridLines(false);
        rchart.getAxisLeft().setDrawGridLines(false);





    }
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case R.id.nav_para:
                Intent h = new Intent(this, activity_second.class);
                startActivity(h);


                break;
            case R.id.nav_analytics:
                Intent j = new Intent(this, activity_progress.class);
                startActivity(j);
                break;
            /*case R.id.nav_analytics:
                Intent i = new Intent(this, activity_analytics.class);
                startActivity(i);
                finish();
                break;*/

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}

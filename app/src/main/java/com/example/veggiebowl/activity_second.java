package com.example.veggiebowl;

import android.content.Intent;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import android.icu.util.LocaleData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;

import java.time.LocalDateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static com.example.veggiebowl.sign_up.newdate;
import static com.example.veggiebowl.sign_up.newmonth1;
import static com.example.veggiebowl.sign_up.newyear;


public class activity_second extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    TextView txt2;
    public static int dayCount;
    private static final String TAG= "CalenderActivity";
    private CalendarView mcalenderView;
    DatabaseReference sensors1,sensors;
    TextView txtwater2,txtnutrient2,txtheight2;
    TextView txt3;
    TextView txt4;


    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);



        navigationView = (NavigationView) findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mcalenderView=(CalendarView) findViewById(R.id.calendarView);
        sensors=FirebaseDatabase.getInstance().getReference("sensor");
//        txt2=(TextView) findViewById(R.id.txt2);
      txt3=(TextView) findViewById(R.id.txt3);
        //txt4=(TextView) findViewById(R.id.txt4);
        txtwater2=(TextView) findViewById(R.id.txtwater2);
        txtnutrient2=(TextView) findViewById(R.id.txtnutrient2);
        txtheight2=(TextView) findViewById(R.id.txtheight2);
        /*Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
        String formattedDate = df.format(c);*/
        /*String dayOfMonthStr = String.valueOf(dayOfMonth);*/

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        mcalenderView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int datee) {
                month=month+1;
                String date= datee + "/" + month +"/" + year;
                DateTimeZone BRAZIL = DateTimeZone.forID("Asia/Calcutta");
                DateTime start = new DateTime(signin_activity.year2,signin_activity.month2,signin_activity.date2, 0, 0, 0, BRAZIL);
                DateTime end = new DateTime(year, month, datee, 0, 0, 0, BRAZIL);
                dayCount= Days.daysBetween(start.toLocalDate(), end.toLocalDate()).getDays();
                if(dayCount>0)
                {
                    String dis = Integer.toString(dayCount);
                    txt3.setText(dis);
                    }
                else
                {
                    txt3.setText("0");
                }
                //txt2.setText(date);
                if(dayCount<30)
                {
                    txtnutrient2.setText("-");
                }
                if(dayCount==30)
                {
                    txtnutrient2.setText("5g/litre");

                }
                else if(dayCount>=31 && dayCount<=59)
                {
                    txtnutrient2.setText("-");
                }
                else if(dayCount==60)
                {
                    txtnutrient2.setText("10g/litre");
                }
                else if(dayCount>=61 && dayCount<=89)
                {
                    txtnutrient2.setText("-");
                }

                else if(dayCount==90)
                {
                    txtnutrient2.setText("15g/litre");
                }
                else if(dayCount>=91 && dayCount<=100)
                {
                    txtnutrient2.setText("-");
                }

                sensors1=FirebaseDatabase.getInstance().getReference().child("sensor").child("ultrasonic");
                sensors1.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        String distance=dataSnapshot.child("distance4").getValue().toString();
                        Log.d("tha",distance);
                        float conv = Float.parseFloat(distance);
                        DecimalFormat d = new DecimalFormat();

                        d.setMaximumFractionDigits(2);

                        txtheight2.setText(d.format(conv) + "cm");
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                sensors=FirebaseDatabase.getInstance().getReference().child("sensor").child("water");
                sensors.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        String water=dataSnapshot.child("water").getValue().toString();
                        txtwater2.setText(water);

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });









            }
        });



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